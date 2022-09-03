//
//  ProgramEntriesView.swift
//  iosApp
//
//  Created by Alexander Dobrynin on 31.08.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

let programEntries = DummyData.shared.ProgramEntries().sorted(by: {$0.timeRange.start < $1.timeRange.start})

struct ProgramEntriesView: View {
    
    @State private var searchText = ""
    
    private var searchResults: [ProgramEntryPreview] {
        if searchText.isEmpty {
            return programEntries
        } else {
            return programEntries //.filter { $0.contains(searchText) }
        }
    }
    
    var body: some View {
        NavigationView {
            List {
                Section(header: HStack {
                    VStack { Divider() }
                    Text(timeFormatter(timeRange: programEntries[0].timeRange)[0])
                        .font(.headline)
                    VStack { Divider() }
                  }) {
                ForEach(searchResults, id: \.self) { programEntry in
                    NavigationLink(destination: Text(programEntry.id)) {
                        HStack {
                            Divider().background(.red)
                            VStack (alignment: .leading) {
                                listContent(programEntry: programEntry)
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
    func listContent(programEntry: ProgramEntryPreview) -> some View {
        let formatColor = Color(UIColor(hex: programEntry.format.hexColor) ?? UIColor(Color.red))
        let formattedTime = timeFormatter(timeRange: programEntry.timeRange)
        VStack (alignment: .leading) {
            HStack {
                Text(programEntry.format.label)
                    .foregroundColor(formatColor)
                    .padding(6)
                    .overlay(RoundedRectangle(cornerRadius: 20)
                                .stroke(formatColor, lineWidth: 1))
                Spacer()
                Image(systemName: "star")
                    .foregroundColor(.yellow)
            }
            VStack (alignment: .leading) {
                Text(formattedTime[0] + " - " + formattedTime[1] + " Uhr")
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
    
    func timeFormatter(timeRange: TimeRange) -> [String] {
        let formatter = DateFormatter()
        let start = Date(timeIntervalSince1970: Double(timeRange.start)/1000.0)
        let end = Date(timeIntervalSince1970: Double(timeRange.end)/1000.0)
        formatter.dateFormat = "HH:mm"
        return [formatter.string(from: start), formatter.string(from: end)]
    }
}

extension UIColor {
    public convenience init?(hex: String) {
        let r, g, b, a: CGFloat
        
        if hex.hasPrefix("#") {
            let start = hex.index(hex.startIndex, offsetBy: 1)
            let hexColor = String(hex[start...])
            
            if hexColor.count == 8 {
                let scanner = Scanner(string: hexColor)
                var hexNumber: UInt64 = 0
                
                if scanner.scanHexInt64(&hexNumber) {
                    r = CGFloat((hexNumber & 0xff000000) >> 24) / 255
                    g = CGFloat((hexNumber & 0x00ff0000) >> 16) / 255
                    b = CGFloat((hexNumber & 0x0000ff00) >> 8) / 255
                    a = CGFloat(hexNumber & 0x000000ff) / 255
                    
                    self.init(red: r, green: g, blue: b, alpha: a)
                    return
                }
            }
        }
        
        return nil
    }
}

//func generateSearchableString(programEntry: ProgramEntryPreview) {
//    let programEntriesString = [String]
//    ForEach(programEntries, id: \.self) { programEntry in
//        let programEntryString =
//        programEntry.name
//        + programEntry.tags.name
//        + programEntry.room.name
//    }
//}
