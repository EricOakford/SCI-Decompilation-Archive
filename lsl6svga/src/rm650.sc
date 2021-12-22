;;; Sierra Script 1.0 - (do not remove this comment)
(script# 650)
(include sci.sh)
(use Main)
(use fileScr)
(use LarryRoom)
(use Print)
(use PolyPath)
(use Polygon)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm650 0
)

(local
	local0
)
(instance rm650 of LarryRoom
	(properties
		picture 650
		horizon 0
	)
	
	(method (init)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: 3
					init:
						0
						142
						287
						142
						287
						117
						273
						114
						216
						92
						196
						90
						187
						78
						143
						75
						136
						67
						117
						67
						111
						77
						79
						90
						100
						96
						43
						122
						0
						113
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 112 92 178 92 183 118 148 122 112 120
					yourself:
				)
		)
		(ego
			posn: 129 67
			init:
			setSpeed: 6
			normalize: 900 2
			setHeading: 180
		)
		(thunderBird init:)
		(lamp init: setPri: 85 ignoreActors: 1)
		(glass init: ignoreActors: 1)
		(glass1 init: ignoreActors: 1)
		(door init: setPri: 50)
		(super init: &rest)
		(theMusic2 stop:)
		(theMusic
			number: 337
			play:
			setLoop: -1
			mute: 1 4
			mute: 1 5
			mute: 1 6
			mute: 1 7
			mute: 1 8
			mute: 1 9
		)
		(= gLarryRoom curRoom)
		(theIconBar enableIcon: (ScriptID 0 8) show:)
		(curRoom setScript: sDoThunder)
	)
	
	(method (dispose)
		(Bclr 74)
		((ScriptID 92 3) view: 1900)
		(super dispose:)
	)
	
	(method (cue)
		(if (talkers size:) (messager cue: 1))
		(= gGButtonBarGetCursor theCursor)
		(theGame setCursor: normalCursor)
		(SetCursor 225 90)
		(if
			(Print
				width: 200
				font: userFont
				addTitle: 0 8 0 2 650
				addText: 0 8 0 1 50 2 650
				addIcon: 1911 0 0 0 0
				addButton: 0 2 8 0 1 50 33 650
				addButton: 1 1 8 0 1 120 33 650
				init:
			)
			(if global205 (proc79_7))
			(theMusic number: 0 stop:)
			(if (not (Btst 254)) (theGame changeScore: 20 254))
			(if (not (ego has: 11)) (ego get: 11))
			(thePlane drawPic: -1 10)
			(curRoom newRoom: 620)
		else
			(theIconBar enableIcon: (ScriptID 0 8) show:)
			(= gLarryRoom curRoom)
		)
		(theGame setCursor: gGButtonBarGetCursor)
	)
)

(instance sDoThunder of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 92)
				(proc79_8 11)
				(= seconds 2)
			)
			(1 (messager say: 0 0 1 1 self))
			(2
				(theMusic mute: 0 4)
				(ego
					cycleSpeed: 6
					moveSpeed: 6
					setMotion: MoveTo 170 90 self
				)
			)
			(3
				(thunderBird
					cycleSpeed: 9
					moveSpeed: 9
					setCycle: End self
				)
				(door setCycle: End self)
			)
			(4)
			(5
				(sfx number: 33 play:)
				(ego setHeading: 315 self)
			)
			(6 (= cycles 2))
			(7
				(thunderBird
					view: 651
					loop: 0
					cel: 0
					x: 119
					y: 74
					setCycle: End self
				)
			)
			(8
				(messager sayRange: 0 0 1 2 3 self)
			)
			(9
				(theMusic mute: 0 5)
				(messager sayRange: 0 0 1 4 5 self)
			)
			(10
				(ego setMotion: PolyPath 136 139 self)
			)
			(11 (ego setHeading: 180 self))
			(12 (= cycles 2))
			(13
				(ego
					view: 653
					setLoop: 0 1
					cel: 0
					posn: 136 139
					setPri: 199
					setSpeed: 11
					setCycle: 0
				)
				(UnLoad 128 900)
				(= cycles 5)
			)
			(14
				(ego setScript: sDrink)
				(thunderBird
					setLoop: 1 1
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 92 100
				)
			)
			(15
				(theMusic mute: 0 6)
				(thunderBird
					loop: 2
					cel: 0
					setSpeed: 16
					setCycle: CT 2 1 self
				)
			)
			(16
				(theGame changeScore: 20 254)
				(= ticks 180)
			)
			(17
				(thunderBird setCycle: CT 2 1 self)
			)
			(18
				(proc79_6 1430)
				(thunderBird setCycle: End self)
			)
			(19
				(ego setScript: sDrink)
				(dress init: ignoreActors: 1)
				(thunderBird
					view: 652
					setLoop: 0 1
					cel: 0
					cycleSpeed: 9
					moveSpeed: 9
					setCycle: Walk
					setMotion: MoveTo 129 138 self
				)
			)
			(20)
			(21
				(= next sThunderMovesIn)
				(self dispose:)
			)
		)
	)
)

(instance sThunderMovesIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bclr 92)
				(messager sayRange: 0 0 1 6 8 self)
			)
			(1
				(ego view: 98)
				(theMusic mute: 0 7)
				(thunderBird
					view: 653
					loop: 1
					cel: 0
					posn: 136 139
					setSpeed: 9
					setCycle: 0
					setCycle: CT 3 1 self
				)
			)
			(2
				(sfx number: 572 play:)
				(ego get: 11)
				(thunderBird setCycle: End self)
			)
			(3
				(messager sayRange: 0 0 1 9 10 self)
			)
			(4
				(thunderBird loop: 2 cel: 0 setCycle: CT 4 1 self)
			)
			(5
				(sfx number: 572 play:)
				(thunderBird setCycle: End self)
			)
			(6 (= cycles 2))
			(7
				(messager say: 0 0 1 11 self)
			)
			(8 (= cycles 2))
			(9
				(messager say: 0 0 1 12 self)
			)
			(10
				(thunderBird loop: 3 cel: 0 setCycle: CT 3 1 self)
			)
			(11
				(messager say: 0 0 1 13 self)
				(thunderBird cel: 3)
				(Bset 74)
				(UpdateScreenItem ((ScriptID 92 3) view: 1901))
			)
			(12
				(theMusic mute: 0 8 9)
				(sfx number: 654 play:)
				(thunderBird setCycle: End self)
			)
			(13
				(thunderBird
					view: 652
					setLoop: 0 1
					cel: 0
					posn: 129 138
					setCycle: Walk
					setMotion: MoveTo 119 128
				)
				(ego
					view: 657
					loop: 1
					setPri: 120
					cel: 0
					setCycle: End self
					show:
				)
			)
			(14
				(messager say: 0 0 1 14 self)
			)
			(15
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(16
				(= next sAssumeThePosition)
				(self dispose:)
			)
		)
	)
)

(instance sAssumeThePosition of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 1 15 self)
				(theMusic number: 338 loop: 1 play:)
			)
			(1
				(ego
					loop: 3
					cel: 0
					posn: 144 105
					setPri: -1
					setCycle: End self
				)
			)
			(2
				(messager say: 0 0 1 16 self)
			)
			(3
				(messager say: 0 0 1 17 self)
			)
			(4
				(thunderBird
					view: 652
					setLoop: 1 1
					cel: 0
					setCycle: Walk
					setMotion: MoveTo 133 120 self
				)
			)
			(5
				(thunderBird
					view: 656
					loop: 1
					cel: 0
					posn: 134 121
					setCycle: End self
				)
			)
			(6
				(thunderBird hide:)
				(lamp dispose:)
				(ego view: 656 loop: 0 cel: 0 setSpeed: 6 posn: 142 105)
				(= cycles 2)
			)
			(7
				(Load rsVIEW 654)
				(messager say: 0 0 1 18 self)
			)
			(8
				(messager say: 0 0 1 19 self)
			)
			(9
				(messager sayRange: 0 0 1 20 21 self)
			)
			(10 (ego setCycle: End self))
			(11
				(ego view: 654 loop: 0 cel: 0)
				(theMusic number: 656 setLoop: -1 play:)
				(self setScript: sTableCrawl self)
			)
			(12
				(= gLarryRoom 0)
				(theIconBar disableIcon: (ScriptID 0 8))
				(theGame hideControls:)
				(= cycles 2)
			)
			(13
				(cast eachElementDo: #hide)
				(ego setScript: 0)
				(thePlane drawPic: -1 10)
				(theMusic fade:)
				(= ticks 90)
			)
			(14
				(Print font: userFont addText: 0 0 1 22 modeless: 2 init:)
				(= ticks 360)
			)
			(15
				(Print modeless: 0)
				(if (Print dialog?) ((Print dialog?) dispose:))
				(= cycles 2)
			)
			(16 (curRoom newRoom: 620))
		)
	)
)

(instance sTableCrawl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Load rsVIEW 1906)
				(= cycles 2)
			)
			(1
				(if (< (++ local0) 5)
					(UpdateScreenItem ((ScriptID 92 3) view: 1906 cel: 1))
				)
				(sfx number: 652 play:)
				(ego loop: 0 cel: 0 setCycle: End self)
			)
			(2
				(sfx number: 903 play:)
				(UpdateScreenItem ((ScriptID 92 3) view: 1901 loop: 1))
				(ego loop: 1 cel: 0 setCycle: End self)
			)
			(3
				(if (>= local0 4)
					(self dispose:)
				else
					(self changeState: 1)
				)
			)
		)
	)
)

(instance sDrink of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(glass hide:)
				(ego setCycle: End self)
			)
			(1 (= cycles (Random 60 120)))
			(2 (ego setCycle: Beg self))
			(3
				(glass show:)
				(sDoThunder cue:)
				(self dispose:)
			)
		)
	)
)

(instance thunderBird of Actor
	(properties
		x 118
		y 74
		view 655
	)
)

(instance lamp of Prop
	(properties
		x 143
		y 109
		view 650
		loop 3
	)
)

(instance door of Prop
	(properties
		x 115
		y 73
		view 6500
	)
)

(instance dress of View
	(properties
		x 92
		y 100
		view 650
	)
)

(instance glass of View
	(properties
		x 138
		y 134
		priority 199
		fixPriority 1
		view 650
		loop 2
	)
)

(instance glass1 of View
	(properties
		x 123
		y 134
		priority 199
		fixPriority 1
		view 650
		loop 2
	)
)

(instance sfx of Sound
	(properties)
)
