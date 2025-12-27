import SwiftUI
import Shared

@main
struct iOSApp: App {
    
    init() {
//        KoinHelper().doInitKoin()
        KoinHelperKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
