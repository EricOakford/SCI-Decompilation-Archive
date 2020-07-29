;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm440 0
)

(instance rm440 of LLRoom
	(properties
		lookStr {The F.B.I. laboratory is filled with interesting equipment, most of which baffles you.}
		picture 440
		style SCROLLLEFT
		east 450
		west 430
	)
	
	(method (init)
		(SetFFRoom 450)
		(LoadMany SOUND 40 442)
		(LoadMany VIEW 440 432 440 442 444 1430 1440)
		(Load SCRIPT RANDCYC)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 189
						0 140
						192 140
						231 130
						251 138
						305 125
						293 113
						271 102
						0 102
						0 0
						319 0
						319 189
					yourself:
				)
		)
		(ego init: normalize: x: 20)
		(if (not (Btst fSeeFartDemonstration))
			(Load SOUND 443 444 445 446 447 448 449)
			(fartman init: setScript: sMix)
		)
		(HandsOff)
		(self setScript: sRoom)
		(super init:)
		(if (!= prevRoomNum 430)
			(ego y: 118 view: 570)
		)
		(door init: stopUpd:)
		(computer init: stopUpd:)
		(duck setCycle: Forward init:)
		(machine init:)
		(contraption init:)
		(books init:)
		(desk init:)
		(southBank init:)
	)
	
	(method (dispose)
		(theMusic2 fade:)
		(super dispose:)
	)
)

(instance sRoom of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setMotion: MoveTo 20 (ego y?) self)
			)
			(1
				(twit
					init:
					setCycle: StopWalk -1
					setMotion: MoveTo 40 100 self
				)
			)
			(2
				(if (Btst fBeenIn440)
					(HandsOn)
					(self dispose:)
				else
					(Bset fBeenIn440)
					(= seconds 2)
				)
			)
			(3
				(Say Commander_Twit 440 0 #dispose)
				(= seconds 3)
			)
			(4
				(twit setMotion: MoveTo 133 100 self)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: PolyPath 114 105
				)
			)
			(5 (= ticks 60))
			(6
				(twit view: 442 setLoop: 0 setCycle: RandCycle)
				(= ticks 60)
			)
			(7
				(soundFX number: 442 loop: -1 flags: 0 play:)
				(computer startUpd: setCycle: RandCycle)
				(= seconds 4)
			)
			(8
				(Say Commander_Twit 440 1 #dispose #caller self)
			)
			(9 (= seconds 4))
			(10
				(SolvePuzzle 1)
				(Say Commander_Twit 440 2 #dispose)
				(= seconds 3)
			)
			(11
				(Say Commander_Twit 440 3 #dispose)
				(= seconds 2)
			)
			(12
				(Say Commander_Twit 440 4 #dispose)
				(= seconds 3)
			)
			(13
				(Say Commander_Twit 440 5 #dispose)
				(= seconds 2)
			)
			(14
				(Say Commander_Twit 440 6 #dispose)
				(= seconds 2)
			)
			(15
				(Say Commander_Twit 440 7 #dispose)
				(= seconds 2)
			)
			(16
				(Say ego 440 8 #at -1 185)
				(= ticks 90)
			)
			(17
				(Say Commander_Twit 440 9 #dispose)
				(= seconds 3)
			)
			(18
				(Say Commander_Twit 440 10 #dispose #caller self)
			)
			(19
				(soundFX loop: 1 flags: 1 stop:)
				(computer setCycle: 0 stopUpd:)
				(twit
					view: 432
					setCycle: StopWalk -1
					setHeading: 180 self
				)
			)
			(20
				(Say Commander_Twit 440 11 #dispose)
				(SetFFRoom 0)
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

(instance sToDoc of Script
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				state
				(not register)
				(< (ego x?) 231)
				(< (ego x?) (- (twit x?) 10))
			)
			(= register 1)
			(ego
				cycleSpeed: 6
				moveSpeed: 6
				setMotion: PolyPath 231 108
			)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Say Commander_Twit 440 12 #dispose #caller self)
			)
			(1
				(twit
					setLoop: -1
					setCycle: StopWalk -1
					setMotion: MoveTo 260 100 self
				)
			)
			(2
				(door setCycle: EndLoop self)
				(soundFX number: 40 setLoop: 1 play:)
			)
			(3
				(twit setCycle: 0)
				(Say Commander_Twit 440 13 #dispose #caller self)
			)
			(4
				(Say Dr__Phil_Hopian 440 14 #dispose #caller self)
			)
			(5
				(ego setMotion: MoveTo 286 106 self)
			)
			(6
				(Say ego 440 15 67 -1 185)
				(= cycles 10)
			)
			(7
				(ego setMotion: MoveTo 330 (ego y?) self)
			)
			(8
				(Say Dr__Phil_Hopian 440 16 #dispose #caller self)
			)
			(9 (= seconds 3))
			(10
				(Say Dr__Phil_Hopian 440 17 #dispose #caller self)
			)
			(11
				(door setCycle: BegLoop self)
				(soundFX number: 41 setLoop: 1 play:)
			)
			(12
				(TimePrint 440 18)
				(TimePrint 440 19)
				(theMusic2 fade:)
				(curRoom newRoom: 450)
			)
		)
	)
)

(instance sFart of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset fSeeFartDemonstration)
				(Say Commander_Twit 440 20 #dispose #caller self)
			)
			(1
				(Face ego fartman)
				(= seconds 2)
			)
			(2
				(Say Commander_Twit 440 21 #dispose #caller self)
			)
			(3 (= seconds 3))
			(4
				(fartman setScript: 0 setCycle: EndLoop self)
			)
			(5
				(fartman
					setLoop: 1
					setCel: 0
					cycleSpeed: 24
					setCycle: EndLoop self
				)
			)
			(6 (= seconds 3))
			(7
				(soundFX number: 444 setLoop: 1 play:)
				(fartman setLoop: 2 setCel: 0 setCycle: EndLoop self)
			)
			(8 (= seconds 3))
			(9
				(soundFX number: 445 play:)
				(fartman setLoop: 3 setCel: 0 setCycle: CycleTo 2 1 self)
			)
			(10 (= seconds 2))
			(11
				(fartman
					setCycle: EndLoop
					setPri: 11
					setMotion: MoveTo 235 32 self
				)
				(soundFX number: 446 play:)
			)
			(12
				(soundFX number: 447 play:)
				(fartman setCel: 8 setMotion: MoveTo 293 71 self)
			)
			(13
				(soundFX number: 448 play:)
				(fartman setCel: 8 setMotion: MoveTo 252 135 self)
			)
			(14
				(soundFX number: 449 play:)
				(fartman setMotion: MoveTo 159 14 self)
			)
			(15
				(soundFX number: 448 play:)
				(fartman setMotion: MoveTo 112 149 self)
			)
			(16
				(soundFX number: 449 play:)
				(fartman setMotion: MoveTo 114 17 self)
			)
			(17
				(soundFX number: 447 play:)
				(fartman setMotion: MoveTo 169 138 self)
			)
			(18
				(soundFX number: 448 play:)
				(fartman setMotion: MoveTo 240 11 self)
			)
			(19
				(soundFX number: 449 play:)
				(fartman setMotion: MoveTo 288 68 self)
			)
			(20
				(soundFX number: 448 play:)
				(fartman setMotion: MoveTo 252 135 self)
			)
			(21
				(soundFX number: 443 setLoop: -1 play:)
				(fartman
					setCel: 8
					setLoop: 4
					setMotion: MoveTo 183 76 self
				)
			)
			(22
				(fartman
					setLoop: 5
					setCycle: Forward
					setStep: 12 2
					cycleSpeed: 6
					setMotion: MoveTo -50 (fartman y?) self
				)
			)
			(23
				(fartman dispose:)
				(soundFX fade:)
				(= seconds 3)
			)
			(24
				(Say Commander_Twit 440 22 #dispose #caller self)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance twit of Actor
	(properties
		x -15
		y 100
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
				(< x 133)
				(> (- (ego x?) (self x?)) 20)
			)
			(self setMotion: MoveTo (Min 133 (+ x 10)) y)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 440 23)
			)
			(verbTalk
				(if (not (Btst fSeeFartDemonstration))
					(HandsOff)
					(curRoom setScript: sFart)
				else
					(Say Commander_Twit 440 24 #dispose)
				)
			)
			(verbZipper
				(TimePrint 440 25)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance door of Prop
	(properties
		x 282
		y 99
		description {Doctor Hopian's door}
		lookStr {Through this door lies the offices of Dr. Phil Hopian, F.B.I. gynecologist.}
		view 440
		loop 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(HandsOff)
				(curRoom setScript: sToDoc)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance computer of Prop
	(properties
		x 133
		y 61
		description {the computer}
		sightAngle 40
		lookStr {If only you had paid attention when Larry chattered on and on about his computer. You know nothing about this computer and are sure you'll be unable to do anything with it.}
		view 440
		loop 2
		signal ignrAct
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 440 26)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance fartman of Actor
	(properties
		x 173
		y 157
		yStep 12
		view 444
		signal (| ignrAct ignrHrz)
		cycleSpeed 24
		illegalBits $0000
		xStep 12
	)
	
	(method (doVerb)
		(SolvePuzzle 3 fFartPoints)
		(HandsOff)
		(curRoom setScript: sFart)
	)
)

(instance sMix of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: CycleTo 1 1 self)
			)
			(1 (= seconds (Random 2 4)))
			(2 (client setCycle: EndLoop self))
			(3 (self init:))
		)
	)
)

(instance duck of Prop
	(properties
		x 45
		y 167
		z 7
		description {the duck}
		sightAngle 40
		lookStr {If it drinks like a duck, and it quacks like a duck, it must be an experiment.}
		view 440
		loop 3
		priority 15
		signal (| ignrAct fixPriOn)
		detailLevel 3
	)
)

(instance Commander_Twit of Talker
	(properties
		nsTop 15
		nsLeft 40
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
		cycleSpeed 30
	)
)

(instance twitMouth of Prop
	(properties
		nsTop 47
		nsLeft 11
		view 1430
		cycleSpeed 8
	)
)

(instance Dr__Phil_Hopian of Talker
	(properties
		nsTop 15
		nsLeft 50
		view 1440
		loop 3
		viewInPrint TRUE
		name "Dr. Phil Hopian"
	)
	
	(method (init)
		(= bust peltBust)
		(= eyes peltEyes)
		(= mouth peltMouth)
		(super init: &rest)
	)
)

(instance peltBust of Prop
	(properties
		view 1440
		loop 1
	)
)

(instance peltEyes of Prop
	(properties
		nsTop 18
		nsLeft 13
		view 1440
		loop 2
		cycleSpeed 30
	)
)

(instance peltMouth of Prop
	(properties
		nsTop 21
		nsLeft 14
		view 1440
		cycleSpeed 8
	)
)

(instance machine of Feature
	(properties
		x 215
		y 47
		nsTop -1
		nsLeft 170
		nsBottom 95
		nsRight 261
		description {the machine}
		sightAngle 40
		lookStr {This machine does something, you feel quite certain.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 440 27)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance contraption of Feature
	(properties
		x 114
		y 160
		z 34
		nsTop 115
		nsLeft 94
		nsBottom 137
		nsRight 134
		description {the chemistry set}
		sightAngle 40
		lookStr {This must be the source of the flatulence powder.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 440 28)
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
		y 166
		nsTop 144
		nsBottom 189
		nsRight 319
		description {the workbench}
		sightAngle 40
		lookStr {Lots of chemicals, beakers, and junk food wrappers fill this workbench.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 440 29)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance books of Feature
	(properties
		x 41
		y 88
		z 30
		nsTop 49
		nsLeft 28
		nsBottom 68
		nsRight 55
		description {the books}
		sightAngle 40
		lookStr {These books are filled with Latin phrases. The only Latin phrases you know would get a sailor thrown out of a bar in Tijuana!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 440 30)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance desk of Feature
	(properties
		x 81
		y 87
		z 17
		nsTop 54
		nsBottom 88
		nsRight 162
		description {the desk}
		sightAngle 40
		lookStr {This desk doesn't contain any secret documents; they all were burned in the big fire last week!}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbDo
				(TimePrint 440 31)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance soundFX of Sound)
