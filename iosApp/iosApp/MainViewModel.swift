//
//  MainViewModel.swift
//  iosApp
//
//  Created by Iqbal Bobobox on 27/12/25.
//
import Foundation
import Combine
import Shared

@MainActor
final class MainViewModel: ObservableObject {

    let repository = KoinProvider().githubRepository
    private var task: Task<Void, Never>?
    
    @Published var users: [UserResponse] = []
    @Published var username: String = ""
    @Published var errorMessage: String = ""
    
    func getUsers() {
        task?.cancel()
        
        task = Task {
            for await result in repository.getUsers() {
                handle(result)
                break
            }
        }
    }
    
    private func handle(_ result: Any?) {
        switch result {
        case let success as DataResultSuccess<NSArray>:
            users = success.data as? [UserResponse] ?? []
            
        case let error as DataResultError:
            errorMessage = error.t.message ?? "Unknown error"
            
        default:
            break
        }
    }
    
    deinit {
        task?.cancel()
    }
}
