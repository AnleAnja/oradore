import SwiftUI

@main
struct iOSApp: App {
  @StateObject private var viewModel = AppViewModel()
  
  var body: some Scene {
    WindowGroup {
      MainView()
        .environmentObject(viewModel)
    }
  }
}
