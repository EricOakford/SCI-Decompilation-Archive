;;; Sierra Script 1.0 - (do not remove this comment)
(script# 890)
(include game.sh)
(use Main)
(use LLRoom)
(use Door)
(use Patti)
(use Osc)
(use RandCyc)
(use Polygon)
(use Feature)
(use LoadMany)
(use Reverse)
(use Sound)
(use Motion)
(use Invent)
(use Actor)
(use System)

(public
	rm890 0
)

(local
	local0
	pressedPowerButton
	pressedRewind
	local3
	local4
	triedToLeave
	talkCount
	mixerUseCount
	local8
	tapeRewound
	local10
)
(instance rm890 of LLRoom
	(properties
		lookStr {You are inside K-RAP radio's Control Room B. For such a large room, it certainly looked smaller from the outside. The walls are filled with a myriad of recording equipment, electronic gear, mixers, microphones, recording tape, etc. etc.}
		picture 890
		south 880
	)
	
	(method (init)
		(LoadMany VIEW 872 880 882 883 890 891)
		(LoadMany SOUND 891 146 800 801 802)
		(LoadMany SCRIPT REVERSE)
		(HandsOn)
		(SetFFRoom 0)
		(ego
			init:
			actions: ActionsKRAP
			normalize: 872
			x: 70
			y: 185
		)
		(super init:)
		(fElectronics1 init: approachVerbs: verbDo verbUse verbLook)
		(fElectronics2 init: approachVerbs: verbDo verbUse verbLook)
		(fElectronics3 init: approachVerbs: verbDo verbUse verbLook)
		(fElectronics4 init: approachVerbs: verbDo verbUse verbLook)
		(fElectronics5 init: approachVerbs: verbDo verbUse verbLook)
		(fElectronics6 init:)
		(fElectronics7 init:)
		(speaker1 init:)
		(speaker2 init:)
		(speaker3 init:)
		(tapeShelf init: approachVerbs: verbDo verbLook)
		(shelf init: approachVerbs: verbDo verbLook)
		(recorder init: approachVerbs: verbDo verbUse verbLook)
		(tape init: approachVerbs: verbDo verbUse verbLook)
		(if (== ((Inventory at: iReelToReelTape) owner?) 3)
			(tape setLoop: 0 x: 35 y: 142 stopUpd:)
		else
			(tape stopUpd:)
		)
		(mixer init: approachVerbs: verbDo verbLook)
		(microphone init: approachVerbs: verbDo verbTalk verbLook)
		(glass init: stopUpd:)
		(lights init: approachVerbs: verbDo verbLook stopUpd:)
		(if (Btst fHeardScrew)
			(lights setCycle: Forward startUpd:)
		)
		(addToPics doit:)
		(recSignA init: setCycle: Forward)
		(hammer_a init: setCycle: RandCycle cycleSpeed: 12)
		(self
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 189
						98 189
						103 187
						235 187
						289 166
						284 154
						265 161
						189 133
						112 133
						17 176
						29 189
						0 189
					yourself:
				)
		)
	)
	
	(method (doit)
		(cond 
			(script (script doit:))
			((ego edgeHit?)
				(if local3
					(if (not triedToLeave)
						(TimePrint 890 0)
						(= triedToLeave TRUE)
					)
				else
					(curRoom newRoom: 880)
				)
			)
			(triedToLeave
				(= triedToLeave FALSE)
			)
			(else
				(super doit:)
			)
		)
	)
	
	(method (dispose)
		(LoadMany FALSE REVERSE)
		(super dispose:)
	)
)

(instance sCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego hide:)
				(tape hide:)
				(glass hide:)
				(lights hide:)
				(recSignA dispose:)
				((curRoom obstacles?) dispose:)
				(theIconBar disable:)
				(curRoom drawPic: 880 picture: 880 curPic: 880)
				(theIconBar enable:)
				(doorA init: close:)
				(doorB init:)
				(mikestand init: stopUpd:)
				(recSign init: setCycle: Forward)
				(addToPics doit:)
				(hammer_a
					setLoop: -1
					view: 882
					x: 65
					y: 86
					xStep: 8
					yStep: 4
					cycleSpeed: 12
					moveSpeed: 12
					setCycle: Walk
					setMotion: MoveTo 156 123 self
				)
			)
			(1
				(TimePrint 890 1)
				(hammer_a
					view: 883
					xStep: 3
					yStep: 2
					cycleSpeed: 6
					moveSpeed: 6
					setLoop: 2
					setMotion: MoveTo 186 123 self
				)
				(mikestand startUpd: setMotion: MoveTo 205 123)
			)
			(2
				(= local3 1)
				(mikestand stopUpd:)
				(hammer_a
					setLoop: -1
					view: 882
					xStep: 8
					yStep: 4
					cycleSpeed: 12
					moveSpeed: 12
					setCycle: Walk
					setMotion: MoveTo 30 124 self
				)
			)
			(3
				(TimePrint 890 2)
				(TimePrint 890 3)
				(hammer_a setMotion: MoveTo -30 124 self)
			)
			(4
				(doorA dispose:)
				(doorB dispose:)
				(mikestand dispose:)
				(hammer_a dispose:)
				(recSign dispose:)
				(theIconBar disable:)
				(curRoom
					drawPic: 890
					picture: 890
					curPic: 890
					addObstacle:
						((Polygon new:)
							type: PBarredAccess
							init:
								0 0
								319 0
								319 189
								98 189
								103 187
								235 187
								289 166
								284 154
								265 161
								189 133
								112 133
								17 176
								29 189
								0 189
							yourself:
						)
				)
				(theIconBar enable:)
				(ego show:)
				(tape show:)
				(glass show: stopUpd:)
				(lights show:)
				(= seconds 3)
			)
			(5
				(TimePrint 890 4)
				(TimePrint 890 5)
				(TimePrint 890 6)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fElectronics1 of Feature
	(properties
		x 150
		y 199
		nsTop 165
		nsLeft 117
		nsBottom 189
		nsRight 184
		description {the giant speaker}
		sightAngle 40
		approachX 151
		approachY 187
		lookStr {You could swear these are genuine Altec "Voice of the Theatre" speakers. You haven't seen these babies in years!}
	)
)

(instance fElectronics2 of Feature
	(properties
		x 283
		y 187
		nsTop 163
		nsLeft 247
		nsBottom 189
		nsRight 319
		description {the electronic equipment}
		sightAngle 40
		approachX 235
		approachY 187
		lookStr {Everywhere you look, you see buttons, switches, lights, reels of tape, and tape drives.}
	)
)

(instance fElectronics3 of Feature
	(properties
		x 301
		y 169
		nsTop 129
		nsLeft 283
		nsBottom 163
		nsRight 319
		description {the electronic equipment}
		sightAngle 40
		approachX 281
		approachY 169
		lookStr {Everywhere you look, you see buttons, switches, lights, reels of tape, and tape drives.}
	)
)

(instance fElectronics4 of Feature
	(properties
		x 277
		y 151
		nsTop 106
		nsLeft 235
		nsBottom 135
		nsRight 319
		description {the left large-hub reel-to-reel recorder}
		sightAngle 40
		approachX 237
		approachY 151
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 890 7)
			)
			(verbLook
				(TimePrint 890 8)
			)
			(verbUse
				(switch theItem
					(iReelToReelTape
						(TimePrint 890 9)
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

(instance fElectronics5 of Feature
	(properties
		x 215
		y 139
		nsTop 106
		nsLeft 196
		nsBottom 125
		nsRight 235
		description {the right large-hub reel-to-reel recorder}
		sightAngle 40
		approachX 205
		approachY 139
	)
	
	(method (doVerb theVerb theItem)
		(fElectronics4 doVerb: theVerb theItem &rest)
	)
)

(instance fElectronics6 of Feature
	(properties
		x 265
		y 63
		nsTop 17
		nsLeft 274
		nsBottom 109
		nsRight 319
		description {the electronic equipment}
		sightAngle 40
		lookStr {Everywhere you look, you see buttons, switches, lights, reels of tape, and tape drives. Unfortunately, they are much too high for you to reach.}
	)
)

(instance fElectronics7 of Feature
	(properties
		x 265
		y 63
		nsTop 30
		nsLeft 212
		nsBottom 109
		nsRight 274
		description {the electronic equipment}
		sightAngle 40
		lookStr {Everywhere you look, you see buttons, switches, lights, reels of tape, and tape drives. Unfortunately, they are much too high for you to reach.}
	)
)

(instance speaker1 of Feature
	(properties
		x 100
		y 60
		nsTop 43
		nsLeft 86
		nsBottom 78
		nsRight 114
		description {the monitor speaker}
		sightAngle 40
		lookStr {You could swear these are genuine Altec "Voice of the Theatre" speakers. You haven't seen these babies in years!}
	)
)

(instance speaker2 of Feature
	(properties
		x 199
		y 57
		nsTop 42
		nsLeft 186
		nsBottom 72
		nsRight 212
		description {the monitor speaker}
		sightAngle 40
		lookStr {You could swear these are genuine Altec "Voice of the Theatre" speakers. You haven't seen these babies in years!}
	)
)

(instance speaker3 of Feature
	(properties
		x 10
		y 24
		nsBottom 49
		nsRight 21
		description {the monitor speaker}
		sightAngle 40
		lookStr {You could swear these are genuine Altec "Voice of the Theatre" speakers. You haven't seen these babies in years!}
	)
)

(instance shelf of Feature
	(properties
		x 154
		y 87
		nsTop 63
		nsLeft 120
		nsBottom 131
		nsRight 189
		description {the equipment rack}
		sightAngle 40
		approachX 155
		approachY 139
		lookStr {This shelf contains a lot of electronic equipment, most of it old, plus a shelf filled with blank reel-to-reel tapes.}
	)
)

(instance tapeShelf of Feature
	(properties
		x 154
		y 87
		nsTop 79
		nsLeft 138
		nsBottom 102
		nsRight 169
		description {the shelf of tapes}
		sightAngle 40
		approachX 155
		approachY 139
		lookStr {Why, here's what you need! A shelf filled with blank, reel-to-reel recording tapes.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(if (!= ((Inventory at: iReelToReelTape) owner?) 0)
					(TimePrint 890 10)
				else
					(ego setScript: GetTape)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance mixer of Feature
	(properties
		x 70
		y 144
		nsTop 107
		nsLeft 66
		nsBottom 126
		nsRight 104
		description {the microphone mixer}
		sightAngle 40
		approachX 90
		approachY 140
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(switch (++ talkCount)
					(1 (TimePrint 890 11))
					(2 (TimePrint 890 12))
					(3 (TimePrint 890 13))
					(4 (TimePrint 890 14))
					(5 (TimePrint 890 15))
					(6 (TimePrint 890 16))
					(7 (TimePrint 890 17))
					(else  (TimePrint 890 18))
				)
			)
			(verbDo
				(if (== (++ mixerUseCount) 1)
					(TimePrint 890 19)
				else
					(ego setScript: useMixer)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance microphone of Feature
	(properties
		x 59
		y 145
		nsTop 102
		nsLeft 50
		nsBottom 120
		nsRight 68
		description {the microphone}
		sightAngle 40
		approachX 89
		approachY 141
		lookStr {A professional-quality, high-fidelity, studio ribbon microphone hangs from a boom over the studio console.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 890 20)
			)
			(verbTalk
				(if local3
					(if local8
						(ego setScript: useMikeScript)
					else
						(TimePrint 890 21)
					)
				else
					(TimePrint 890 22)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance recorder of Feature
	(properties
		x 26
		y 163
		nsTop 115
		nsBottom 146
		nsRight 52
		description {the reel-to-reel tape recorder}
		approachX 42
		approachY 163
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(cond 
					(pressedPowerButton
						(ego setScript: turnRecorderOff)
					)
					((== ((Inventory at: iReelToReelTape) owner?) 3)
						(cond 
							(tapeRewound
								(ego setScript: getRecordedTape)
							)
							(pressedRewind
								(ego setScript: rewindTape)
							)
							((Btst fHeardScrew)
								(ego setScript: turnRecorderOn)
							)
							(else
								(TimePrint 890 23)
							)
						)
					)
					(else
						(TimePrint 890 24)
					)
				)
			)
			(verbLook
				(cond 
					(pressedPowerButton (TimePrint 890 25))
					((== ((Inventory at: iReelToReelTape) owner?) 3)
						(cond 
							(tapeRewound (TimePrint 890 26))
							(pressedRewind (TimePrint 890 27))
							(else (TimePrint 890 28))
						)
					)
					(else (TimePrint 890 29))
				)
			)
			(verbUse
				(switch theItem
					(iReelToReelTape
						(if pressedRewind
							(TimePrint 890 30)
						else
							(ego setScript: mountTapeScript)
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

(instance tape of Prop
	(properties
		x 42
		y 130
		description {the tape}
		approachX 42
		approachY 163
		lookStr {A large Studer console recorder is all warmed up and ready to record--except there's no tape upon which to record!}
		view 890
		loop 1
	)
	
	(method (doVerb theVerb theItem)
		(recorder doVerb: theVerb theItem)
	)
)

(instance tapeHole of PicView
	(properties
		x 158
		y 85
		view 890
		loop 7
	)
	
	(method (doVerb theVerb theItem)
		(tapeShelf doVerb: theVerb theItem)
	)
)

(instance hammer_a of Actor
	(properties
		x 38
		y 120
		description {P. C. Hammer}
		lookStr {P. C. Hammer is working in the next Control Room.}
		view 883
		loop 3
	)
)

(instance glass of Prop
	(properties
		x 41
		y 116
		description {the glass window}
		lookStr {A heavy, soundproof, plate-glass window separates you from the outside--and your freedom!}
		view 890
		loop 2
		cel 1
		priority 9
		signal fixPriOn
	)
)

(instance lights of Prop
	(properties
		x 53
		y 122
		description {the microphone mixer}
		approachX 90
		approachY 144
		view 890
		loop 3
		priority 9
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(mixer doVerb: theVerb theItem &rest)
			)
			(verbLook
				(mixer doVerb: theVerb theItem &rest)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance recSignA of Prop
	(properties
		x 17
		y 51
		description {the recording sign}
		lookStr {This sign is illuminated whenever anyone is recording inside control room A.}
		view 890
		loop 6
		cel 1
		cycleSpeed 12
	)
)

(instance useMikeSound of Sound
	(properties
		flags $0001
		number 891
	)
)

(instance breakGlassSound of Sound
	(properties
		number 146
		priority 11
	)
)

(instance useMikeScript of Script
	
	(method (changeState newState &tmp i invSave)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 89 141 self)
			)
			(1
				(ego
					view: 891
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(2
				(useMikeSound play:)
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(TimePrint 890 31)
				(glass startUpd: setCel: 2)
				(= ticks 123)
			)
			(4
				(breakGlassSound play:)
				(glass setCycle: EndLoop self)
			)
			(5 (= seconds 3))
			(6
				(TimePrint 890 32)
				(TimePrint 890 33)
				(= seconds 4)
			)
			(7
				(ego
					setLoop: 4
					setPri: 9
					setCel: 0
					cycleSpeed: 10
					setCycle: EndLoop self
				)
			)
			(8
				(ego actions: 0 setLoop: 5 setCel: 0 setCycle: EndLoop self)
			)
			(9
				(SolvePuzzle 15 fUsedMike)
				(= ticks 123)
			)
			(10
				(ego
					view: 891
					posn: 18 114
					actions: 0
					setCel: 0
					setLoop: 7
					setCycle: 0
				)
				(= ticks 123)
			)
			(11
				(ego setMotion: MoveTo 1 114 self)
			)
			(12 (= seconds 3))
			(13
				(theMusic fade: 0 15 12 1)
				(theIconBar disable:)
				(DrawPic 1 -32762)
				(= ticks 30)
			)
			(14
				(theIconBar enable:)
				(TimePrint 890 34)
				(= seconds 3)
			)
			(15
				(TimePrint 890 35)
				(= seconds 3)
			)
			(16
				(= i 0)
				(while (< i (Inventory size?))
					(if ((= invSave (Inventory at: i)) ownedBy: 1000)
						(invSave owner: 24)
					)
					(++ i)
				)
				(curRoom newRoom: 200)
			)
		)
	)
)

(instance GetTape of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 891
					setLoop: 0
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 30))
			(2
				(tapeHole init:)
				(addToPics doit:)
				(ego setCel: 255 setCycle: BegLoop self)
			)
			(3
				(ego get: iReelToReelTape normalize: 872 setHeading: 0)
				(= ticks 30)
			)
			(4
				(SolvePuzzle 4 fGotReelToReelTape)
				(TimePrint 890 36)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance turnRecorderOn of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 891
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(tape setCycle: Reverse startUpd:)
				(ego normalize: 872 loop: 4 cel: 1 setHeading: 270)
				(= ticks 30)
			)
			(2
				(SolvePuzzle 4 fTurnedOnKRAPRecorder)
				(TimePrint 890 37)
				(= pressedPowerButton 1)
				(= seconds 7)
			)
			(3
				(TimePrint 890 38)
				(= seconds 3)
			)
			(4
				(hammer_a setLoop: 1 setCycle: EndLoop self)
			)
			(5
				(TimePrint 890 39)
				(= seconds 3)
			)
			(6
				(hammer_a
					cel: 0
					setCycle: 0
					setMotion: MoveTo 10 120 self
				)
			)
			(7
				(TimePrint 890 40)
				(glass startUpd:)
				(= cycles 5)
			)
			(8
				(self dispose:)
				(curRoom setScript: sCartoon)
			)
		)
	)
)

(instance turnRecorderOff of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 891
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1
				(tape setCycle: 0 stopUpd:)
				(= ticks 30)
			)
			(2
				(= pressedPowerButton FALSE)
				(= pressedRewind TRUE)
				(ego normalize: 872 loop: 4 cel: 1 setHeading: 270)
				(= ticks 30)
			)
			(3
				(TimePrint 890 41)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance rewindTape of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 891
					setLoop: 2
					setCel: 0
					cycleSpeed: 12
					setCycle: EndLoop self
				)
			)
			(1 (= ticks 30))
			(2
				(ego normalize: 872 loop: 4 cel: 1 setHeading: 270)
				(tape setCycle: Forward startUpd: cycleSpeed: 0)
				(= seconds 7)
			)
			(3
				(tape setCycle: 0 stopUpd:)
				(= ticks 30)
			)
			(4
				(TimePrint 890 42)
				(= tapeRewound 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance getRecordedTape of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 891
					setLoop: 1
					setCel: 3
					cycleSpeed: 12
					setCycle: CycleTo 2 -1 self
				)
			)
			(1
				(tape setLoop: 1 setCel: 0 x: 42 y: 130 stopUpd:)
				(ego cycleSpeed: 12 setCycle: BegLoop self)
			)
			(2
				(ego get: iReelToReelTape)
				((Inventory at: iReelToReelTape) state: 1)
				(ego normalize: 872 loop: 4 cel: 1 setHeading: 270)
				(= ticks 30)
			)
			(3
				(SolvePuzzle 7 fRecordedReelToReelTape)
				(TimePrint 890 43)
				(= ticks 30)
			)
			(4 (HandsOn) (self dispose:))
		)
	)
)

(instance useMixer of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 891
					setLoop: 2
					cycleSpeed: 12
					setCel: 0
					setCycle: Oscillate 1 self
				)
			)
			(1
				(ego loop: 4 cel: 1 setHeading: 270 normalize: 872)
				(= ticks 30)
			)
			(2
				(switch mixerUseCount
					(1)
					(2
						(TimePrint 890 44)
						(Wait 60)
						(TimePrint 890 45)
					)
					(3
						(TimePrint 890 46)
						(Wait 60)
						(TimePrint 890 47)
					)
					(4
						(TimePrint 890 48)
						(Wait 60)
						(TimePrint 890 49 67 -1 185)
					)
					(5
						(TimePrint 890 50)
						(Wait 60)
						(TimePrint 890 51)
					)
					(6
						(TimePrint 890 52)
						(Wait 60)
						(TimePrint 890 53)
					)
					(else 
						(cond 
							((not (Btst fHeardScrew)) (ego setScript: sHearingScrew))
							(local3
								(if local8
									(TimePrint 890 54)
								else
									(TimePrint 890 55)
									(= local8 1)
								)
							)
							(else (TimePrint 890 56))
						)
					)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sHearingScrew of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(TimePrint 890 57)
				(lights setCycle: Forward startUpd:)
				(= seconds 3)
			)
			(1
				(TimePrint 890 58)
				(= seconds 3)
			)
			(2
				(SolvePuzzle 8 fHeardScrew)
				(TimePrint 890 59)
				(= local4 1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance mountTapeScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Patti put: iReelToReelTape 3)
				(ego
					view: 891
					setLoop: 1
					cycleSpeed: 12
					setCel: 0
					setCycle: CycleTo 2 1 self
				)
			)
			(1
				(tape setLoop: 0 x: 35 y: 142)
				(ego setCycle: EndLoop self)
			)
			(2
				(ego normalize: 872 loop: 4 cel: 1 setHeading: 270)
				(= ticks 30)
			)
			(3
				(TimePrint 890 60)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance doorA of Door
	(properties
		x 47
		y 77
		description {the Control Room A door}
		sightAngle 40
		approachX 64
		approachY 80
		lookStr {Be careful, Patti! Inside that door stands the man you've come here to investigate: P. C. Hammer!}
		view 880
		loop 1
		signal ignrAct
		locked 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 890 61)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance doorB of Door
	(properties
		x 199
		y 111
		description {the Control Room B door}
		sightAngle 40
		approachX 218
		approachY 116
		lookStr {A sign on the door reads "Control Room B." It is currently unoccupied.}
		view 880
		loop 2
		entranceTo 890
		locked 1
		moveToX 220
		moveToY 116
		enterType 0
		exitType 0
	)
)

(instance hammer_b of Actor
	(properties
		x 82
		y 79
		description {P. C. Hammer}
		lookStr {Through the control room window, you see P. C. Hammer mixing his "live" radio broadcast for tomorrow morning's drive time show.}
		view 883
	)
)

(instance mikestand of Actor
	(properties
		x 175
		y 121
		description {the microphone stand}
		lookStr {A large boom microphone stand sits just outside the door to Control Room B.}
		view 880
		signal ignrAct
	)
)

(instance recSign of Prop
	(properties
		x 177
		y 42
		description {the recording sign}
		lookStr {This sign is illuminated whenever anyone is recording inside this control room.}
		view 880
		loop 3
		cel 1
		cycleSpeed 12
	)
)

(instance ActionsKRAP of Actions
	(properties)
	
	(method (doit)
		(return FALSE)
	)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(verbDo
					(TimePrint 890 62)
					(return TRUE)
				)
				(verbZipper
					(TimePrint 890 63)
					(return TRUE)
				)
				(else
					(return FALSE)
				)
			)
		)
	)
)
