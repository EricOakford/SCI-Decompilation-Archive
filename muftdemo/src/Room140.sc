;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh)
(use Main)
(use Procs)
(use Tactor)
(use TScripts)
(use Intrface)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	Room140 0
)

(local
	local0
	local1
)
(instance Room140 of FRoom
	(properties
		lookStr {From here you can see far away.}
		picture 140
		style HSHUTTER
		east 150
		south 190
		southX 180
		southY 162
		eastX 120
		eastY 149
	)
	
	(method (init)
		(LoadMany VIEW 624 625 627 628)
		(LoadMany SOUND 3 25 4 114 132 133 134 135)
		(super init:)
		(theGame setScript: theZapCursor)
		(theMusic number: 3 priority: 15 setLoop: -1 play:)
		(theIconBar enable: show:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 173
						0 0
						319 0
						319 147
						309 145
						302 148
						294 143
						247 133
						234 138
						226 130
						216 132
						196 133
						183 117
						167 113
						154 112
						135 97
						121 94
						50 84
						48 90
						36 93
						31 106
						9 100
						6 117
						76 167
						148 167
						153 173
					yourself:
				)
		)
		(jack
			view: 623
			setLoop: 0
			selection: 0
			posn: 77 124
			setCycle: Forward
			init:
		)
		(Face ego jack)
		(stalk ignoreActors: 0 stopUpd: init:)
	)
	
	(method (dispose)
		(if (theMusic handle?)
			(theMusic fade: 0 15 5 1)
		)
		(super dispose: &rest)
	)
	
	(method (cue)
		(super cue:)
		(self setScript: returnAxe)
	)
)

(instance enterAxeGone of HandsOffScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Face ego jack)
				(= ticks 120)
			)
			(1
				(Say 2 jack ego self 1 140 0)
			)
			(2
				(jack setCycle: CycleTo 12 1 self)
			)
			(3
				(Say 3 ego jack self 1 140 1)
			)
			(4
				(jack setCycle: CycleTo 3 1)
				(Say 2 jack ego self 1 140 2)
				(jack setCycle: Forward)
			)
			(5
				(Print 140 3
					#at 70 150
					#color 32
					#time 5
					#dispose
					self
				)
			)
			(6
				(curRoom newRoom: 150)
				(= walkSound 6)
				(self dispose:)
			)
		)
	)
)

(instance returnAxe of HandsOffScript
	
	(method (doit)
		(super doit: &rest)
		(if (and local1 (== (theMusic prevSignal?) -1))
			(= local1 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= ticks 90))
			(1
				(theGame setCursor: 69)
				(Face ego jack)
				(theJackStory incState:)
				(theJackStory done: TRUE)
				(++ global145)
				(= gGJackStory theJackStory)
				(= cycles 1)
			)
			(2
				(jack setCycle: CycleTo 5 1 self)
			)
			(3
				(Say 2 jack ego self 1 140 4)
			)
			(4
				(Say 0 ego jack self 1 140 5)
			)
			(5
				(Face ego jack)
				(ego setMotion: PolyPath 98 130 self)
			)
			(6
				(theIconBar curInvIcon: iAxe show:)
				((inventory at: iAxe) owner: 0)
				(Say 0 jack ego self 1 140 6)
				(proc10_28)
			)
			(7
				(ego setMotion: PolyPath 110 150)
				(if (theMusic2 handle?)
					(theMusic2 stop:)
				)
				(theMusic number: 25 priority: 15 setLoop: 1 play:)
				(jack view: 624 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(8
				(Face ego jack)
				(jack
					view: 626
					setLoop: 0
					cycleSpeed: 12
					cel: 0
					setCycle: CycleTo 3 1 self
				)
			)
			(9
				(ShakeScreen 2 shakeSDown)
				(chopS play:)
				(chip
					posn: (- (jack x?) 22) (- (jack y?) 28)
					setPri: 8
					cel: 0
					init:
				)
				(jack setCycle: EndLoop self)
			)
			(10
				(jack setCycle: CycleTo 3 1 self)
			)
			(11
				(ShakeScreen 4 shakeSDown)
				(chopS play:)
				(chip cel: 1)
				(jack setCycle: EndLoop self)
			)
			(12
				(jack setCycle: CycleTo 3 1 self)
			)
			(13
				(ShakeScreen 4 shakeSDown)
				(chopS play:)
				(chip cel: 2)
				(jack setCycle: EndLoop self)
			)
			(14
				(giantS number: 133 setLoop: -1 play:)
				(jack view: 627 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(15
				(giant
					ignoreActors: TRUE
					setLoop: 0
					cel: 0
					posn: 200 24
					setCycle: Forward
					init:
				)
				(Face ego giant)
				(= cycles 1)
			)
			(16
				(giant yStep: 5 setMotion: MoveTo 200 72 self)
			)
			(17
				(giantS number: 134 setLoop: 1 play:)
				(theMusic number: 4 priority: 15 setLoop: -1 play:)
				(giant setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(18
				(giantHole setCycle: EndLoop self init:)
				(ShakeScreen 8 shakeSDown)
			)
			(19
				(giantS number: 135 setLoop: 1 play:)
				(giantEyes setCycle: Forward init: setPri: 10)
				(= cycles 15)
			)
			(20
				(jack view: 628 setLoop: 0 cel: 0 setCycle: EndLoop self)
			)
			(21
				(Print 140 7
					#at 70 150
					#color 14
					#time 5
					#dispose
					self
				)
			)
			(22
				(= walkSound 0)
				(curRoom newRoom: 355)
			)
		)
	)
)

(instance enterBeansRet of HandsOffScript
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local0 1)
				(PutInRoom (inventory at: iAxe))
				(= cycles 120)
			)
			(1
				(Say 2 jack ego self 1 140 0)
			)
			(2
				(theJackStory incState:)
				(self dispose:)
			)
		)
	)
)

(instance egoDo140 of Code
	
	(method (doit theVerb)
		(switch theVerb
			(verbTalk
				(switch (theJackStory state?)
					(3
						(if local0
							(switch (ego selection?)
								(0
									(Say 2 ego jack -1 1 140 8)
									(Say 0 jack ego -1 0 140 9)
									(Say 0 jack ego -1 0 140 10)
									(Say 0 jack ego -1 0 140 11)
									(Say 0 jack ego -1 0 140 12)
									(Say 0 jack ego -1 0 140 13)
									(Say 1 jack ego -1 0 140 14)
									(Say 3 jack ego -1 1 140 15)
								)
								(1
									(Say 2 ego jack -1 1 140 16)
									(Say 1 jack ego -1 0 140 17)
									(Say 3 jack ego -1 1 140 18)
								)
								(2
									(Say 2 ego jack -1 1 140 19)
									(Say 3 jack ego -1 1 140 20)
								)
								(else 
									(Say 0 ego jack -1 1 140 21)
									(Say 0 jack ego -1 1 140 22)
								)
							)
						else
							(Say 0 ego jack -1 1 140 21)
							(Say 2 jack ego -1 1 140 23)
						)
					)
				)
				(ego incSel:)
			)
		)
	)
)

(instance stalk of View
	(properties
		x 60
		y 114
		description {Stalk}
		lookStr {***It is a very big stalk}
		view 140
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(Print 140 24)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chip of Prop
	(properties
		view 629
	)
)

(instance giantEyes of Prop
	(properties
		x 179
		y 62
		view 625
		loop 3
	)
)

(instance giantHole of Prop
	(properties
		x 200
		y 72
		view 625
		loop 2
	)
)

(instance giant of Actor
	(properties
		view 625
	)
)

(instance jack of Tactor
	(properties
		talkerID 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(switch (theJackStory state?)
					(3
						(if local0
							(switch (self selection?)
								(0 (Say 3 self ego -1 1 140 25))
								(1 (Say 3 self ego -1 1 140 26))
								(2 (Say 3 self ego -1 1 140 27))
								(else 
									(Say 0 self ego -1 1 140 28)
								)
							)
						else
							(Say 2 self ego -1 1 140 29)
						)
					)
				)
				(self incSel:)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (setUp)
		((= talkerObj jackTalkObj)
			setUp: jackBust jackEyes jackMouth
		)
	)
)

(instance jackTalkObj of TalkerObj
	(properties
		x -1
		y 97
		nsTop 10
		nsLeft 108
		view 621
	)
)

(instance jackBust of View
	(properties
		nsTop 27
		nsLeft 40
		view 621
		loop 6
	)
)

(instance jackEyes of Prop
	(properties
		nsTop 31
		nsLeft 42
		view 621
		loop 4
		cycleSpeed 36
	)
)

(instance jackMouth of Prop
	(properties
		nsTop 38
		nsLeft 39
		view 621
		loop 2
		cycleSpeed 12
	)
)

(instance wall of Feature
	(properties
		x 39
		y 140
		description {Wall}
		sightAngle 90
		onMeCheck cBLUE
		lookStr {This is part of the wall that goes around the town.}
	)
)

(instance tree of Feature
	(properties
		x 123
		y 35
		description {Tree}
		sightAngle 90
		onMeCheck cGREEN
		lookStr {The trees are old and very tall.}
	)
)

(instance hill of Feature
	(properties
		x 169
		y 70
		description {Hill}
		sightAngle 90
		onMeCheck cCYAN
		lookStr {The hills are near the mountains.}
	)
)

(instance mountain of Feature
	(properties
		x 173
		y 35
		description {Mountain}
		sightAngle 90
		onMeCheck cRED
		lookStr {The mountains are on the far side of the hills.}
	)
)

(instance cliff of Feature
	(properties
		x 74
		y 90
		description {Cliff}
		sightAngle 90
		onMeCheck cMAGENTA
		lookStr {The sides of the cliffs are steep.}
	)
)

(instance rock of Feature
	(properties
		x 287
		y 120
		description {Rock}
		sightAngle 90
		onMeCheck cBROWN
		lookStr {This rock is very big.}
	)
)

(instance flower of Feature
	(properties
		x 205
		y 119
		description {Flowers}
		sightAngle 90
		onMeCheck cLGREY
		lookStr {The flowers here smell like bubble gum.}
	)
)

(instance grass of Feature
	(properties
		x 157
		y 103
		description {Grass}
		sightAngle 90
		onMeCheck cGREY
		lookStr {The grass is green.}
	)
)

(instance chopS of Sound
	(properties
		flags mNOPAUSE
		number 132
	)
)

(instance giantS of Sound
	(properties
		flags mNOPAUSE
		number 133
		loop -1
	)
)
