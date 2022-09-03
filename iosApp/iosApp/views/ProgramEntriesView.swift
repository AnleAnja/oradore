//
//  ProgramEntriesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

let programEntries = Dictionary(grouping: DummyData.shared.ProgramEntries(), by: { $0.timeRange.start })

struct ProgramEntriesView: View {
    
    @State private var searchText = ""
    
    private var searchResults: [Int64 : [ProgramEntryPreview]] {
        if searchText.isEmpty {
            return programEntries
        } else {
            return programEntries.filter { key, value in
                value.contains { entry in
                    entry.name.lowercased().contains(searchText.lowercased()) ||
                    entry.room.name.lowercased().contains(searchText.lowercased()) ||
                    entry.speakers.contains { speaker in
                        speaker.firstName.lowercased().contains(searchText.lowercased()) ||
                        speaker.lastName.lowercased().contains(searchText.lowercased()) ||
                        speaker.company.lowercased().contains(searchText.lowercased()) ||
                        speaker.jobTitle.lowercased().contains(searchText.lowercased())
                    } ||
                    entry.format.label.lowercased().contains(searchText.lowercased())
                }
            }
        }
    }
    
    var body: some View {
        NavigationView {
            List {
                ForEach(Array(searchResults.keys), id: \.self) { start in
                    Section(header: HStack {
                        VStack { Divider() }
                        Text(timeFormatter(time: start))
                            .font(.headline)
                        VStack { Divider() }
                    }                        )
                    {
                        ForEach(searchResults[start]!, id: \.self) { entry in
                            let formatColor = Color(hexStringToUIColor(hex: entry.format.hexColor))
                            NavigationLink(destination: Text(entry.id)) {
                                HStack {
                                    Divider()
                                        .frame(maxWidth:2)
                                        .background(formatColor)
                                    VStack (alignment: .leading) {
                                        listContent(programEntry: entry, formatColor: formatColor)
                                    }
                                }
                            }
                        }
                    }
                }
                
            }
            .navigationTitle("Programm")
            .searchable(text: $searchText)
        }
    }
    
    @ViewBuilder
    func listContent(programEntry: ProgramEntryPreview, formatColor: SwiftUI.Color) -> some View {
        VStack (alignment: .leading) {
            HStack {
                Text(programEntry.format.label)
                    .foregroundColor(formatColor)
                    .font(.caption)
                Spacer()
                Image(systemName: "star")
                    .foregroundColor(.yellow)
            }
            VStack (alignment: .leading) {
                Text(timeFormatter(time: programEntry.timeRange.start) + " - " + timeFormatter(time: programEntry.timeRange.end) + " Uhr")
                Text(programEntry.room.name)
            }.font(.caption2)
            Text(programEntry.name).font(.title)
        }
        Spacer()
        ForEach(programEntry.speakers, id: \.self) { speaker in
            HStack {
                VStack (alignment: .leading) {
                    Text(speaker.firstName + " " + speaker.lastName).font(.headline)
                    VStack (alignment: .leading) {
                        Text(speaker.company)
                        Text(speaker.jobTitle)
                    }.font(.caption)
                }
                Spacer()
                AsyncImage(url: URL(string: speaker.imgPreview ?? "")) { image in
                    image.resizable()
                } placeholder: {
                    ProgressView()
                }
                .frame(width: 64, height: 64)
                .cornerRadius(64)
            }
        }
    }
    
    func timeFormatter(time: Int64) -> String {
        let formatter = DateFormatter()
        let timeAsDate = Date(timeIntervalSince1970: Double(time)/1000.0)
        formatter.dateFormat = "HH:mm"
        return formatter.string(from: timeAsDate)
    }
    
    func hexStringToUIColor (hex:String) -> UIColor {
        var cString:String = hex.trimmingCharacters(in: .whitespacesAndNewlines).uppercased()
        
        if (cString.hasPrefix("#")) {
            cString.remove(at: cString.startIndex)
        }
        
        if ((cString.count) != 6) {
            return UIColor.gray
        }
        
        var rgbValue:UInt64 = 0
        Scanner(string: cString).scanHexInt64(&rgbValue)
        
        return UIColor(
            red: CGFloat((rgbValue & 0xFF0000) >> 16) / 255.0,
            green: CGFloat((rgbValue & 0x00FF00) >> 8) / 255.0,
            blue: CGFloat(rgbValue & 0x0000FF) / 255.0,
            alpha: CGFloat(1.0)
        )
    }
}
