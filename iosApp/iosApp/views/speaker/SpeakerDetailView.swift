//
//  RoomDetailView.swift
//  iosApp
//
//  Created by Anja on 04.09.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SpeakerDetailView: View {
    let speaker: Speaker
    
    var body: some View {
        ScrollView {
            VStack (alignment: .leading) {
                if let url = speaker.imgLarge {
                    AsyncImage(url: URL(string: url)) { image in
                        image
                            .resizable()
                            .scaledToFill()
                            .frame(maxHeight: 570)
                            .clipped()
                        
                    } placeholder: {
                        ProgressView()
                    }
                }
                VStack (alignment: .leading){
                    VStack (alignment: .leading) {
                        Text("\(speaker.firstName) \(speaker.lastName)").font(.title)
                        Text(speaker.company).font(.subheadline)
                        Text(speaker.jobTitle).font(.subheadline)
                    }
                    VStack (alignment: .leading) {
                        if !speaker.location.isEmpty {
                            HStack {
                                Image(systemName: "mappin")
                                Text(speaker.location).font(.caption)
                            }
                        }
                        if !speaker.website.isEmpty {
                            HStack {
                                Image(systemName: "link")
                                Text(speaker.website).font(.caption2)
                            }
                        }
                        Divider()
                        Text(speaker.bio).font(.body)
                    }.padding(.top)
                }
                .padding()
            }
        }
    }
}
