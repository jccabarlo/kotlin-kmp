import SwiftUI
import Shared

struct DeviceInfoView: View {
    @Environment(\.dismiss)
    private var dismiss
    
    private struct RowItem: Hashable {
        let title: String
        let subtitle: String
    }

    private let items: [RowItem] = {
        let platform = Platform()
        platform.logSystemInfo()

        var result: [RowItem] = [
            .init(
                title: "Operating System",
                subtitle: "\(platform.osName) \(platform.osVersion)"
            ),
            .init(
                title: "Device",
                subtitle: platform.deviceModel
            ),
            .init(
                title: "Density",
                subtitle: "@\(platform.deviceDensity)x"
            )
        ]
        return result
    }()

    var body: some View {
        NavigationStack {
            List {
                ForEach(items, id: \.self) { item in
                    VStack(alignment: .leading) {
                        Text(item.title)
                            .font(.footnote)
                            .foregroundStyle(.secondary)
                        Text(item.subtitle)
                            .font(.body)
                            .foregroundStyle(.primary)
                    }
                    .padding(.vertical, 4)
                }
            }
            .navigationTitle("Device Info")
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        dismiss()
                    } label: {
                        Text("Done")
                            .bold()
                    }
                }
            }
        }
    }
}

#Preview {
    DeviceInfoView()
}
