;;; Sierra Script 1.0 - (do not remove this comment)
(script# 430)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Jump)
(use Motion)
(use Actor)
(use System)

(public
	rm430 0
)

(local
	[braPts 8] = [175 112 246 112 246 120 175 120]
	[braPts2 8] = [212 112 254 112 254 120 212 120]
)
(instance rm430 of LLRoom
	(properties
		lookStr {Although you haven't really thought about, this isn't what you would have pictured an F.B.I. lab to be.}
		picture 430
		east 440
		west 420
	)
	
	(method (init)
		(braPolygon points: @braPts size: 4)
		(braPolygon2 points: @braPts2 size: 4)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						319 103
						133 103
						133 80
						89 80
						9 131
						107 131
						111 128
						190 128
						190 136
						283 136
						294 147
						319 147
						319 189
						0 189
					yourself:
				)
		)
		(LoadMany VIEW 430 430 432 1430)
		(Load SOUND 436)
		(Load SCRIPT JUMP)
		(ego init: edgeHit: 0 normalize:)
		(desmondDoor init:)
		(if (== prevRoomNum 440)
			(ego x: 300)
			(self style: SCROLLRIGHT setScript: sEnter)
			(desmondDoor cue:)
		else
			(LoadMany VIEW 438 1431)
			(LoadMany SOUND 40 41)
			(ego x: 30 y: 105 cycleSpeed: 6 moveSpeed: 6)
			(twit init: posn: 108 104 setCycle: StopWalk -1)
			(HandsOff)
			(self setScript: sRoom)
		)
		(if (not (Btst fSeeVibratorDemonstration))
			(LoadMany SOUND 431 432 433)
			(vibMan init: setScript: sWork)
		)
		(if (not (Btst fSeeBraDemonstration))
			(LoadMany SOUND 434 435)
			(curRoom addObstacle: braPolygon)
			(braMan init: stopUpd:)
			(techMan init:)
		)
		(northDoor init: stopUpd:)
		(ladder setCycle: Forward init:)
		(dartboard init:)
		(monitors init:)
		(plug init:)
		(outlet init:)
		(chair init:)
		(joystick init:)
		(circuitboard init:)
		(northBank init:)
		(southBank init:)
		(super init:)
	)
	
	(method (dispose)
		(DisposeScript 991)
		(super dispose:)
	)
)

(instance sEnter of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(twit
					init:
					x: 400
					y: 101
					setCycle: StopWalk -1
					setMotion: MoveTo 280 101 self
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sWork of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: EndLoop)
				(= seconds (Random 2 3))
			)
			(1 (self init:))
		)
	)
)

(instance sRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(twit setHeading: 270)
				(= cycles 1)
			)
			(1
				(soundFX number: 40 play:)
				(desmondDoor setCycle: EndLoop self)
			)
			(2
				(theMusic2 number: 436 setLoop: -1 play:)
				(desmondDoor stopUpd:)
				(desmond
					init:
					x: 45
					y: 101
					setCycle: Walk
					setMotion: MoveTo 95 (desmond y?)
				)
				(ego setMotion: MoveTo 96 (+ (ego y?) 4) self)
			)
			(3 (= ticks 60))
			(4
				(Say Inspector_Desmond 430 0 #dispose #caller self)
			)
			(5 (= seconds 3))
			(6
				(Say Commander_Twit 430 1 #dispose #caller self)
			)
			(7 (= seconds 3))
			(8
				(Say ego 430 2 #at -1 185)
				(= ticks 60)
			)
			(9
				(Say Commander_Twit 430 3 #dispose #caller self)
			)
			(10 (= ticks 60))
			(11
				(Say ego 430 4 #at -1 185)
				(= ticks 60)
			)
			(12
				(Say Commander_Twit 430 5 #dispose #caller self)
			)
			(13 (= ticks 60))
			(14
				(Say Inspector_Desmond 430 6 #dispose #caller self)
			)
			(15
				(desmond
					setMotion: MoveTo (- (desmond x?) 75) (desmond y?) self
				)
			)
			(16
				(desmond dispose:)
				(desmondDoor setCycle: BegLoop desmondDoor)
				(soundFX number: 41 play:)
				(twit setMotion: MoveTo 154 (- (twit y?) 3) self)
			)
			(17 (Face twit ego self))
			(18
				(ego
					moveSpeed: (theGame egoMoveSpeed?)
					cycleSpeed: (theGame egoMoveSpeed?)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sVibrator of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (Face ego braMan self))
			(1
				(Say ego 430 7)
				(= ticks 60)
			)
			(2
				(Bset fSeeVibratorDemonstration)
				(Say Commander_Twit 430 8 #dispose #caller self)
			)
			(3
				(soundFX number: 431 setLoop: -1 play:)
				(Face ego vibMan self)
			)
			(4
				(vibMan
					setScript: 0
					setLoop: 1
					cycleSpeed: 24
					setCycle: Forward
				)
				(= ticks 180)
			)
			(5
				(vibMan
					setCel: 0
					setLoop: 2
					cycleSpeed: 18
					setCycle: EndLoop self
				)
			)
			(6
				(soundFX number: 432 play:)
				(vibMan setCel: 0 setLoop: 3 setCycle: EndLoop self)
			)
			(7
				(soundFX number: 433 play:)
				(vibMan
					x: (- (vibMan x?) 6)
					y: (- (vibMan y?) 12)
					xStep: 8
					yStep: 8
				)
				(= cycles 1)
			)
			(8
				(vibMan
					setLoop: 4
					cycleSpeed: 4
					moveSpeed: 8
					setStep: 1 1
					setCycle: Forward
					setMotion: JumpTo -30 230 self
				)
			)
			(9
				(soundFX fade:)
				(vibMan dispose:)
				(= ticks 180)
			)
			(10
				(Say Commander_Twit 430 9 108)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sBra of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fSeeBraDemonstration)
				(techMan setScript: sWork)
				(Face ego braMan self)
			)
			(1
				(Say ego 430 10)
				(= ticks 60)
			)
			(2
				(Say Commander_Twit 430 11 #dispose #caller self)
			)
			(3
				(techMan setScript: 0 setCycle: EndLoop self)
			)
			(4
				(techMan setLoop: 6 setCel: 0 setCycle: EndLoop self)
				(braMan setLoop: 1 setCel: 0)
			)
			(5
				(soundFX number: 435 setLoop: 1 play:)
				(techMan setLoop: 1 setCel: 0 setCycle: Forward)
				(braMan setCel: 1)
				(= ticks 120)
			)
			(6
				(Say ego 430 12 #at -1 185)
				(= ticks 60)
			)
			(7 (Face twit ego self))
			(8
				(Say Commander_Twit 430 13 #dispose #caller self)
			)
			(9
				(braMan
					setLoop: 2
					setCel: 0
					cycleSpeed: 24
					setCycle: EndLoop self
				)
			)
			(10
				(braMan setLoop: 3 setCycle: Forward)
				(techMan
					setLoop: 2
					setCel: 255
					cycleSpeed: 24
					setCycle: BegLoop self
				)
			)
			(11
				(TimePrint 430 14)
				(braMan setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(12
				(techMan
					setLoop: 3
					setMotion: MoveTo (- (techMan x?) 30) (- (techMan y?) 10) techMan
				)
				(soundFX number: 434 setLoop: 1 play:)
				(braMan
					setLoop: 4
					setCel: 0
					cycleSpeed: 5
					setCycle: EndLoop self
				)
			)
			(13
				(soundFX number: 434 setLoop: 1 play:)
				(Face twit braMan self)
			)
			(14
				(Say Commander_Twit 430 15 #dispose #caller self)
			)
			(15
				(braMan setLoop: 5 setCel: 0 cycleSpeed: 18)
				(= ticks 66)
			)
			(16 (braMan setCycle: EndLoop self))
			(17 (= ticks 123))
			(18
				(braMan setLoop: 6 setCel: 0 setCycle: EndLoop self)
			)
			(19 (= ticks 123))
			(20
				(braMan setLoop: 5 setCel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(21
				(Say Commander_Twit 430 16 #dispose)
				(braMan setCycle: BegLoop self)
				((curRoom obstacles?) delete: braPolygon)
				(curRoom addObstacle: braPolygon2)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance twit of Actor
	(properties
		x 30
		y 104
		description {Commander Twit}
		lookStr {He's the perfect image of a man more interested in science than in his own self-image!}
		view 432
		cel 2
		signal ignrAct
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(not (self mover?))
				(not (curRoom script?))
				(> (- (ego x?) (self x?)) 20)
			)
			(self setMotion: MoveTo (+ x 10) y)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 17)
			)
			(verbTalk
				(cond 
					((not (Btst fSeeVibratorDemonstration))
						(HandsOff)
						(curRoom setScript: sVibrator)
					)
					((not (Btst fSeeBraDemonstration))
						(HandsOff)
						(curRoom setScript: sBra)
					)
					(else
						(Say Commander_Twit 430 18 #dispose)
					)
				)
			)
			(verbZipper
				(TimePrint 430 19)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance desmond of Actor
	(properties
		x 25
		y 104
		view 438
		signal ignrAct
	)
)

(instance desmondDoor of Prop
	(properties
		x 15
		y 48
		description {Desmond's door}
		lookStr {This door leads to Inspector Desmond's office. It is presently locked.}
		view 430
		loop 1
		priority 14
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 20)
			)
			(verbTalk
				(Say ego 430 21)
				(TimePrint 430 22 #at -1 185)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(self setPri: 7 stopUpd:)
	)
)

(instance northDoor of View
	(properties
		x 106
		y 77
		description {the exterior door}
		lookStr {This door leads outside.}
		view 430
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(Say Commander_Twit 430 23 108)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance vibMan of Actor
	(properties
		x 137
		y 161
		description {the vibrator technician}
		view 434
		priority 15
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb)
		(SolvePuzzle 3 fVibratorPoints)
		(HandsOff)
		(curRoom setScript: sVibrator)
	)
)

(instance braMan of Prop
	(properties
		x 225
		y 114
		description {the bra-slinging technician}
		view 436
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(if (not (Btst fSeeBraDemonstration))
			(SolvePuzzle 3 fBraPoints)
			(HandsOff)
			(curRoom setScript: sBra)
		else
			(super doVerb: theVerb theItem)
		)
	)
)

(instance techMan of Actor
	(properties
		x 197
		y 116
		description {the bra-tender}
		yStep 5
		view 437
		loop 5
		cel 3
		signal ignrAct
		cycleSpeed 10
		xStep 15
	)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== cel 1)
				(== loop 1)
				(== (soundFX prevSignal?) -1)
			)
			(soundFX play:)
		)
	)
	
	(method (doVerb theVerb theItem)
		(if (Btst fSeeBraDemonstration)
			(TimePrint 430 24)
		else
			(braMan doVerb: theVerb theItem)
		)
	)
	
	(method (cue)
		(super cue:)
		(self
			setLoop: 4
			setCel: 0
			cycleSpeed: 12
			setCycle: EndLoop
			setMotion: MoveTo x (+ y 10)
		)
	)
)

(instance ladder of Prop
	(properties
		x 159
		y 193
		description {the Jacob's ladder}
		lookStr {To think: all your life you assumed there was no real purpose for Jacob's ladders!}
		view 430
		loop 2
		priority 15
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance dartboard of Feature
	(properties
		x 64
		y 91
		z 40
		nsTop 37
		nsLeft 54
		nsBottom 65
		nsRight 75
		description {the dart board}
		sightAngle 40
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 430 25)
			)
			(verbDo
				(TimePrint 430 26)
				(TimePrint 430 27 #at -1 185 #width 280)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance monitors of Feature
	(properties
		x 190
		y 90
		z 49
		nsTop 24
		nsLeft 164
		nsBottom 59
		nsRight 216
		description {the monitors}
		sightAngle 40
		lookStr {These monitors are presently disconnected. When they were heavily used many years ago, it was only to play "Jawbreaker!"}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 28)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance plug of Feature
	(properties
		x 83
		y 118
		z 100
		nsTop 3
		nsLeft 71
		nsBottom 33
		nsRight 96
		description {the giant plug}
		sightAngle 40
		lookStr {You wonder exactly what that giant plug plugs into.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 29)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance outlet of Feature
	(properties
		x 147
		y 77
		z 27
		nsTop 43
		nsLeft 142
		nsBottom 57
		nsRight 152
		description {the intercom}
		sightAngle 40
		lookStr {The lab technicians use this intercom to communicate with the front office and Commander Twit.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 30)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance chair of Feature
	(properties
		x 169
		y 130
		nsTop 117
		nsLeft 152
		nsBottom 144
		nsRight 186
		description {the chair}
		sightAngle 40
		lookStr {This office chair prevents you from accessing the intricate equipment below it.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 31)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance joystick of Feature
	(properties
		x 121
		y 160
		z 34
		nsTop 126
		nsLeft 108
		nsBottom 145
		nsRight 135
		description {the joystick}
		sightAngle 40
		lookStr {Wow! What a joystick!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 32)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance circuitboard of Feature
	(properties
		x 68
		y 189
		z 9
		nsTop 172
		nsLeft 46
		nsBottom 189
		nsRight 91
		description {the circuit board}
		sightAngle 40
		lookStr {This looks like a giant circuit board.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 33)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance northBank of Feature
	(properties
		x 234
		y 89
		z 55
		nsTop 1
		nsLeft 155
		nsBottom 77
		nsRight 319
		description {the laboratory tables}
		sightAngle 40
		lookStr {These tables are filled with highly-expensive scientific equipment, all paid for with your tax dollars!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 34)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance southBank of Feature
	(properties
		x 159
		y 125
		nsTop 118
		nsBottom 189
		nsRight 319
		description {the laboratory tables}
		sightAngle 40
		lookStr {These tables are filled with highly-expensive scientific equipment, all paid for with your tax dollars!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 430 34)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance soundFX of Sound
	(properties
		flags mNOPAUSE
	)
)

(instance Commander_Twit of Talker
	(properties
		nsTop 15
		nsLeft 20
		view 1430
		loop 3
		viewInPrint 1
		name "Commander Twit"
	)
	
	(method (init)
		(= bust twitBust)
		(= eyes twitEyes)
		(= mouth twitMouth)
		(super init: &rest)
	)
)

(instance twitBust of Prop
	(properties
		view 1430
		loop 1
	)
)

(instance twitEyes of Prop
	(properties
		view 1430
		loop 2
		cycleSpeed 15
	)
)

(instance twitMouth of Prop
	(properties
		nsTop 47
		nsLeft 11
		view 1430
	)
)

(instance Inspector_Desmond of Talker
	(properties
		nsTop 15
		nsLeft 15
		view 1431
		loop 3
		viewInPrint TRUE
		name "Inspector Desmond"
	)
	
	(method (init)
		(= bust desmondBust)
		(= mouth desmondMouth)
		(= eyes desmondEyes)
		(super init: &rest)
	)
)

(instance desmondBust of Prop
	(properties
		view 1431
		loop 1
	)
)

(instance desmondEyes of Prop
	(properties
		nsTop 27
		nsLeft 31
		view 1431
		loop 2
		cycleSpeed 50
	)
)

(instance desmondMouth of Prop
	(properties
		nsTop 36
		nsLeft 22
		view 1431
	)
)

(instance braPolygon of Polygon
	(properties
		type PBarredAccess
	)
)

(instance braPolygon2 of Polygon
	(properties
		type PBarredAccess
	)
)
