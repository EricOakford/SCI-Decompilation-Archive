;;; Sierra Script 1.0 - (do not remove this comment)
(script# 840)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Polygon)
(use Feature)
(use ForCount)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm840 0
)

(local
	local0
)
(instance rm840 of LLRoom
	(properties
		lookStr {John Krapper's Office is beautifully decorated in the latest high-tech look. Numerous halogen narrow-beam spotlights bathe the otherwise dark room with small brilliantly-white pools of light.}
		picture 840
		east 820
		west 850
	)
	
	(method (init)
		(LoadMany VIEW 840 841 842)
		(LoadMany SOUND 841 843 800 801 802)
		(LoadMany SCRIPT REVERSE)
		(HandsOn)
		(ego init: normalize: (if (Btst fCoveredInToner) 842 else 570))
		(if (Btst fCoveredInToner)
			(ego actions: ActionsKRAP)
		)
		(switch prevRoomNum
			(east
				(lobbyDoor setPri: 12)
				(ego
					posn: 268 152
					setHeading: 270
					edgeHit: 0
					observeControl: cBLUE cGREEN
				)
				(SolvePuzzle 1 fEnteredKRAPOffice)
			)
			(west
				(ego posn: 35 155 setHeading: 90 edgeHit: 0)
			)
			(else 
				(HandsOn)
				(ego posn: 160 160 edgeHit: 0)
			)
		)
		(super init:)
		(johnDoor init: approachVerbs: verbDo verbUse)
		(copier init: approachVerbs: verbDo verbUse verbLook stopUpd:)
		(opener init: approachVerbs: verbDo)
		(table init:)
		(sofa init:)
		(chair init:)
		(lobbyDoor init: approachVerbs: verbDo verbUse caller: rm840)
		(telephone init: approachVerbs: verbLook verbDo)
		(computer init: approachVerbs: verbLook verbDo)
		(stuff init:)
		(lamp1 init:)
		(lamp2 init:)
		(lamp3 init:)
		(lamp4 init:)
		(desk init: approachVerbs: verbDo verbLook verbUse)
		(plant init: approachVerbs: verbDo verbUse)
		(myWindow init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 142
						268 146
						267 139
						255 122
						235 122
						226 113
						92 113
						79 113
						96 119
						96 133
						70 139
						52 147
						33 147
						0 139
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						0 155
						33 155
						4 173
						3 187
						316 187
						316 180
						282 156
						319 156
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						101 116
						220 116
						225 127
						108 127
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						71 142
						148 129
						176 134
						176 141
						119 151
						147 164
						147 173
						84 184
						74 165
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						148 155
						160 149
						175 149
						208 149
						218 151
						239 158
						237 165
						228 170
						161 168
						148 161
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						211 141
						245 135
						262 144
						268 155
						238 155
						218 148
					yourself:
				)
		)
		(if (== prevRoomNum east)
			(TimePrint 840 0)
		)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(LoadMany FALSE REVERSE)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(ActionsKRAP dispose:)
		(super dispose:)
	)
	
	(method (cue)
		(lobbyDoor ignoreActors:)
		(lobbyDoor caller: 0)
	)
)

(instance desk of Feature
	(properties
		x 150
		y 115
		nsTop 90
		nsLeft 113
		nsBottom 126
		nsRight 214
		description {the desk}
		sightAngle 40
		approachX 150
		approachY 112
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					((not (Btst fCoveredInToner))
						(TimePrint 840 1)
						(if (not (ego has: iLetterOpener))
							(TimePrint 840 2)
						else
							(TimePrint 840 3)
						)
					)
					((not (Btst fLookedAtDesk)) (Bset fLookedAtDesk)
						(ego setScript: lookDeskScript)
					)
					((not (ego has: iEvidenceFolder))
						(ego get: iEvidenceFolder)
						(ego setScript: lookPapersScript)
					)
					(else
						(ego setScript: sLookDesk2ndTime)
					)
				)
			)
			(verbDo
				(cond 
					((and (not (ego has: iLetterOpener)) (not (Btst fDeskUnlocked)))
						(SolvePuzzle 4 fGotLetterOpener)
						(TimePrint 840 4)
						(opener hide:)
						(ego get: iLetterOpener)
					)
					((not (Btst fDeskUnlocked))
						(TimePrint 840 5)
					)
					((and (Btst fDeskUnlocked) (not (Btst fLookedAtDesk)))
						(Bset fLookedAtDesk)
						(ego setScript: openDeskScript)
					)
					((and (Btst fLookedAtDesk) (not (ego has: iEvidenceFolder)))
						(ego get: iEvidenceFolder)
						(ego setScript: getPapersScript)
					)
					(else
						(ego setScript: sOpenDesk2ndTime)
					)
				)
			)
			(verbUse
				(switch theItem
					(iLetterOpener
						(if (Btst fDeskUnlocked)
							(TimePrint 840 6)
							(ego put: iLetterOpener)
							(opener show:)
							(Bclr fDeskUnlocked)
						else
							(Bset fDeskUnlocked)
							(ego setScript: pickLockScript)
						)
					)
					(iDeskKey
						(if (Btst fDeskUnlocked)
							(Bclr fDeskUnlocked)
							(ego setScript: lockDeskScript)
						else
							(Bset fDeskUnlocked)
							(ego setScript: unlockDeskScript)
						)
					)
					(iEvidenceFolder
						(if (Btst fDeskUnlocked)
							(ego put: iEvidenceFolder)
							(ego setScript: putPapersScript)
						else
							(TimePrint 840 7)
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plant of Feature
	(properties
		x 248
		y 81
		nsTop 40
		nsLeft 208
		nsBottom 122
		nsRight 290
		description {the potted plant}
		sightAngle 40
		approachX 231
		approachY 120
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(cond 
					((ego has: iDeskKey)
						(TimePrint 840 8)
					)
					((Btst fFoundDeskKey)
						(TimePrint 840 9)
					)
					(else
						(TimePrint 840 10)
						(Bset fFoundDeskKey)
					)
				)
			)
			(verbDo
				(if (not (ego has: iDeskKey))
					(if (Btst fFoundDeskKey)
						(ego setScript: getKeyScript)
					else
						(ego setScript: sPawThruPlant)
					)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(verbUse
				(if (== theItem iDeskKey)
					(ego put: iDeskKey)
					(ego setScript: replaceKeyScript)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance copier of Prop
	(properties
		x 58
		y 138
		z 20
		description {the copier}
		approachX 68
		approachY 138
		lookStr {It's a genuine ZeroZ 9000, the latest in personal copiers.}
		view 840
		loop 3
		priority 10
		signal fixPriOn
		cycleSpeed 18
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (ego has: iPhotocopiedEvidence)
					(TimePrint 840 11)
				else
					(TimePrint 840 12)
				)
			)
			(verbUse
				(switch theItem
					(iEvidenceFolder
						(if (ego has: iPhotocopiedEvidence)
							(TimePrint 840 11)
						else
							(ego setScript: runCopierScript)
						)
					)
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance paper of Prop
	(properties
		x 58
		y 112
		description {the copier}
		approachX 68
		approachY 138
		lookStr {It's a genuine ZeroZ 9000, the latest in personal copiers.}
		view 840
		loop 5
		priority 10
		signal fixPriOn
		cycleSpeed 12
	)
)

(instance johnDoor of Door
	(properties
		x 11
		y 153
		description {the bathroom door}
		approachX 44
		approachY 152
		lookStr {A sign on the door says "Krapper's Crapper." A smaller sign below it says "John's John".}
		view 840
		loop 1
		signal ignrAct
		entranceTo 850
		moveToY 141
		enterType 0
		exitType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					((ego has: iEvidenceFolder)
						(TimePrint 840 13)
					)
					((ego has: iDeskKey)
						(TimePrint 840 14)
					)
					((ego has: iLetterOpener)
						(TimePrint 840 15)
					)
					(else
						(ego ignoreControl: cGREEN)
						(self setPri: 15)
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (close)
		(ego observeControl: cGREEN cBLUE)
		(super close:)
	)
)

(instance lobbyDoor of Door
	(properties
		x 302
		y 153
		description {the lobby door}
		approachX 269
		approachY 159
		lookStr {This is the door through which you entered. If you walked out it, you would return to the lobby.}
		view 840
		loop 2
		signal ignrAct
		entranceTo 820
		moveToX 280
		moveToY 150
		enterType 0
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(ego setScript: peekScript)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (close)
		(self setPri: -1)
		(super close:)
	)
)

(instance table of Feature
	(properties
		x 192
		y 157
		nsTop 149
		nsLeft 152
		nsBottom 165
		nsRight 232
		description {the table}
		sightAngle 40
		lookStr {Krapper has a beautiful glass coffee table.}
	)
)

(instance chair of Feature
	(properties
		x 237
		y 139
		nsTop 120
		nsLeft 222
		nsBottom 152
		nsRight 256
		description {the chair}
		sightAngle 40
		lookStr {This is the kind of furniture you would buy if you ever settled down.}
	)
)

(instance sofa of Feature
	(properties
		x 112
		y 153
		nsTop 126
		nsLeft 83
		nsBottom 178
		nsRight 141
		description {the sofa}
		sightAngle 40
		lookStr {What an unusual sofa! You so love modern furniture.}
	)
)

(instance telephone of Feature
	(properties
		x 194
		y 116
		z 20
		nsTop 96
		nsLeft 187
		nsBottom 106
		nsRight 202
		description {the telephone}
		sightAngle 40
		approachX 200
		approachY 112
		lookStr {John Krapper's telephone has very few buttons. He must be REALLY important!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 840 16)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance computer of Feature
	(properties
		x 137
		y 116
		z 20
		nsTop 88
		nsLeft 127
		nsBottom 105
		nsRight 148
		description {the desk computer}
		sightAngle 40
		approachX 140
		approachY 112
		lookStr {Look at the dust on that keyboard! He must never use his computer!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 840 17)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance myWindow of Feature
	(properties
		x 161
		y 73
		nsTop 39
		nsLeft 66
		nsBottom 107
		nsRight 256
		description {the window}
		sightAngle 40
		lookStr {Krapper's office has a giant window looking out over beautiful downtown Philadelphia.}
	)
)

(instance stuff of Feature
	(properties
		x 53
		y 72
		nsTop 43
		nsLeft 33
		nsBottom 102
		nsRight 73
		description {the shelves}
		sightAngle 40
		lookStr {Krapper's bookcase is filled with items, all of them useless to your mission.}
	)
)

(instance lamp1 of Feature
	(properties
		x 59
		y 23
		nsTop 17
		nsLeft 53
		nsBottom 29
		nsRight 65
		description {the lamp}
		sightAngle 40
		lookStr {The small halogen spotlights cast a pure white light.}
	)
)

(instance lamp2 of Feature
	(properties
		x 99
		y 6
		nsLeft 93
		nsBottom 13
		nsRight 106
		description {the lamp}
		sightAngle 40
		lookStr {The small halogen spotlights cast a pure white light.}
	)
)

(instance lamp3 of Feature
	(properties
		x 163
		y 22
		nsTop 18
		nsLeft 158
		nsBottom 26
		nsRight 169
		description {the lamp}
		sightAngle 40
		lookStr {The small halogen spotlights cast a pure white light.}
	)
)

(instance lamp4 of Feature
	(properties
		x 260
		y 24
		nsTop 20
		nsLeft 253
		nsBottom 29
		nsRight 267
		description {the lamp}
		sightAngle 40
		lookStr {The small halogen spotlights cast a pure white light.}
	)
)

(instance opener of View
	(properties
		x 155
		y 105
		description {the letter opener}
		approachX 187
		approachY 118
		view 840
		priority 9
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 840 18)
			)
			(verbDo
				(TimePrint 840 4)
				(opener hide:)
				(ego get: iLetterOpener)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance copyMachineSound of Sound
	(properties
		flags mNOPAUSE
		number 841
		priority 11
	)
)

(instance copyExplodesSound of Sound
	(properties
		flags mNOPAUSE
		number 842
		priority 11
	)
)

(instance pickLockSound of Sound
	(properties
		flags mNOPAUSE
		number 843
		priority 11
	)
)

(instance peekScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(lobbyDoor setCel: 1)
				(ego setMotion: MoveTo 272 150 self)
			)
			(1
				(ego setLoop: 8 setCel: 0)
				(= seconds 3)
			)
			(2
				(TimePrint 840 19)
				(= seconds 3)
			)
			(3
				(lobbyDoor setCel: 0)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance getKeyScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 7 else 2)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: 2 4
					get: 9
					setHeading: 90
				)
				(= ticks 30)
			)
			(2
				(SolvePuzzle 10 fGotDeskKey)
				(TimePrint 840 20 #at -1 20)
				(HandsOn)
				(ego setScript: 0)
			)
		)
	)
)

(instance sPawThruPlant of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 7 else 2)
					setCel: 0
					cycleSpeed: 18
					setCycle: CycleTo 5 1 self
				)
			)
			(1 (ego setCycle: CycleTo 0 -1 self))
			(2
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					setHeading: 90
				)
				(= ticks 30)
			)
			(3
				(TimePrint 840 21)
				(Bset fFoundDeskKey)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance replaceKeyScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 7 else 2)
					setCel: 255
					cycleSpeed: 18
					setCycle: BegLoop self
				)
			)
			(1
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					setHeading: 90
				)
				(= ticks 30)
			)
			(2
				(TimePrint 840 22 #at -1 20)
				(HandsOn)
				(ego setScript: 0)
			)
		)
	)
)

(instance openDeskScript of Script
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 123))
			(2
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(3 (= ticks 123))
			(4
				(Format @str 840 23 (= KRAPCombination (Random 10000 -536)))
				(SolvePuzzle 4 fOpenedDesk)
				(TimePrint @str)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: 2 4
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance sOpenDesk2ndTime of Script
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 123))
			(2
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(3 (= ticks 123))
			(4
				(Format @str 840 24 KRAPCombination)
				(TimePrint @str)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: 2 4
					setHeading: 180
					loop: 2
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance lookDeskScript of Script
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					cycleSpeed: 18
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 123))
			(2
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(3 (= ticks 123))
			(4
				(Format @str 840 23 (= KRAPCombination (Random 10000 -536)))
				(SolvePuzzle 5 fOpenedDesk)
				(TimePrint @str)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance sLookDesk2ndTime of Script
	
	(method (changeState newState &tmp [str 100])
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 123))
			(2
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(3 (= ticks 123))
			(4
				(Format @str 840 24 KRAPCombination)
				(TimePrint @str)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance pickLockScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1
				(pickLockSound play:)
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(2
				(SolvePuzzle 5 147)
				(TimePrint 840 25)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance unlockDeskScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1
				(pickLockSound play:)
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(2
				(SolvePuzzle 13 fUnlockedDesk)
				(TimePrint 840 26)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance lockDeskScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1
				(pickLockSound play:)
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(2
				(TimePrint 840 27)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance getPapersScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 8 else 1)
					setCel: 0
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(2 (= ticks 123))
			(3
				(SolvePuzzle 5 fGotEvidence)
				(TimePrint 840 28)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance lookPapersScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: (if (Btst fCoveredInToner) 8 else 1)
					setCel: 255
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(1
				(TimePrint 840 29)
				(= ticks 60)
			)
			(2
				(TimePrint 840 30)
				(= ticks 60)
			)
			(3
				(SolvePuzzle 5 145)
				(TimePrint 840 31)
				(= ticks 123)
			)
			(4
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance putPapersScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					setLoop: (if (Btst fCoveredInToner) 8 else 1)
					setCel: 255
					cycleSpeed: 18
					setCycle: BegLoop self
				)
			)
			(1 (= seconds 3))
			(2
				(ego
					loop: (if (Btst fCoveredInToner) 6 else 0)
					setCel: 255
					cycleSpeed: 18
					setCycle: BegLoop self
				)
			)
			(3 (= ticks 60))
			(4
				(TimePrint 840 32)
				(ego
					normalize: (if (Btst fCoveredInToner) 842 else 570)
					observeControl: cBLUE cGREEN
					loop: 2
					setHeading: 180
					setScript: 0
				)
				(HandsOn)
			)
		)
	)
)

(instance runCopierScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 841
					loop: 3
					cycleSpeed: 18
					x: 68
					y: 138
					setCycle: EndLoop self
				)
			)
			(1
				(ego loop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(2
				(SolvePuzzle 12 fPhotocopiedEvidence)
				(TimePrint 840 33)
				(= ticks 123)
			)
			(3
				(ego loop: 4 setCycle: EndLoop self)
			)
			(4
				(copyMachineSound play: setLoop: -1)
				(copier setCel: 0 setCycle: EndLoop self setPri: 9)
			)
			(5
				(copier stopUpd:)
				(= cycles 1)
			)
			(6
				(paper
					init:
					setCel: 0
					setPri: 9
					setCycle: ForwardCounter 5 self
				)
			)
			(7
				(copyMachineSound stop:)
				(= ticks 200)
			)
			(8
				(Say ego 840 34)
				(= seconds 3)
			)
			(9
				(ego loop: 3 setCel: 255 setCycle: BegLoop self)
			)
			(10
				(paper dispose:)
				(copier setCel: 0)
				(Say ego 840 35)
				(= seconds 3)
			)
			(11
				(ego loop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(12
				(Say ego 840 36)
				(= ticks 123)
			)
			(13
				(TimePrint 840 37 #at -1 185)
				(= ticks 60)
			)
			(14
				(ego loop: 4 setCel: 255 setCycle: BegLoop self)
			)
			(15
				(ego normalize: 570 x: 69 y: 140 loop: 1 setHeading: 270)
				(= ticks 60)
			)
			(16
				(copyExplodesSound play:)
				(copier
					view: 840
					startUpd:
					cycleSpeed: 12
					setCel: 0
					setLoop: 4
					setCycle: CycleTo 1 1 self
				)
			)
			(17
				(ego
					normalize: 842
					observeControl: cBLUE cGREEN
					loop: 1
					setHeading: 270
				)
				(copier view: 840 setPri: 11 setCycle: CycleTo 4 1 self)
			)
			(18
				(copier view: 840 setPri: 9 setCycle: EndLoop self)
			)
			(19 (= seconds 3))
			(20
				(Say ego 840 38)
				(copier stopUpd:)
				(Bset fCoveredInToner)
				(ego get: iPhotocopiedEvidence actions: ActionsKRAP setScript: 0)
				(HandsOn)
			)
		)
	)
)

(instance ActionsKRAP of Actions
	
	(method (doit)
		(return FALSE)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(verbLook
					(if (== (ego view?) 842)
						(TimePrint 840 39)
						(return TRUE)
					else
						(TimePrint 840 40)
						(return TRUE)
					)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)
