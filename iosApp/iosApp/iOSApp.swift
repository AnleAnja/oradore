import SwiftUI

@main
struct iOSApp: App {
    @StateObject private var viewModel : AppViewModel
    
    init() {
        self._viewModel = StateObject(wrappedValue: AppViewModel())
    }

	var body: some Scene {
		WindowGroup {
			MainView()
		}
	}
}
