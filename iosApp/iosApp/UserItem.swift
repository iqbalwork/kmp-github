//
//  UserItem.swift
//  iosApp
//
//  Created by Iqbal Bobobox on 27/12/25.
//
import SwiftUI
import Shared

struct UserItem: View {

    let user: UserResponse

    var body: some View {
        HStack(spacing: 12) {

            AsyncImage(url: URL(string: user.avatarUrl ?? "")) { image in
                image
                    .resizable()
                    .scaledToFill()
            } placeholder: {
                Color.gray.opacity(0.3)
            }
            .frame(width: 64, height: 64)
            .clipShape(Circle())

            Text(user.login ?? "")
                .font(.system(size: 20))
        }
        .padding(12)
        .frame(maxWidth: .infinity, alignment: .leading)
        .overlay(
            RoundedRectangle(cornerRadius: 8)
                .stroke(Color.gray, lineWidth: 1)
        )
    }
}
