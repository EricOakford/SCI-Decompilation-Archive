;;; Sierra Script 1.0 - (do not remove this comment)
(script# 120)
(include game.sh)
(use Main)
(use Scaler)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use StopWalk)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm120 0
)

(local
	[local0 201]
)
(instance rm120 of Room
	(properties
		picture 600
	)
	
	(method (init)
		(LoadMany RES_SOUND 600)
		(theMusic number: 604 loop: -1 play:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						0 0
						319 0
						310 52
						256 52
						243 67
						206 74
						151 69
						151 90
						163 93
						226 75
						235 78
						206 130
						231 157
						130 147
						114 137
						119 127
						101 127
						111 136
						104 145
						12 145
						12 177
						157 165
						312 173
						312 127
						319 127
						319 189
						0 189
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						297 75
						297 140
						265 157
						228 133
						227 122
						251 122
						251 120
						231 113
						241 75
					yourself:
				)
		)
		(bat1 init:)
		(fireMarshall init:)
		(waterFall init: setCycle: Forward)
		(ripple_1 init:)
		(ripple_2 init:)
		(ripple_3 init:)
		(ripple_4 init:)
		(fishermanBat init:)
		(tentBat init:)
		(punkBat init:)
		(freeTailBat init:)
		(passPorts init:)
		(batSqueeks client: batNoisesScr init: play:)
		(super init: &rest)
		(self setScript: demoRm120Scr)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(theMusic fade: 0 10 10 0)
	)
)

(instance demoRm120Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(ego
					view: 11
					x: 39
					y: 40
					setCel: 0
					setLoop: 1
					setPri: 3
					setStep: 3 6
					moveSpeed: 3
					setScale: 0
					ignoreActors:
					init:
				)
				(= seconds 2)
			)
			(2
				(ego setMotion: MoveTo 32 61 self)
			)
			(3
				(ego setCel: 2 setMotion: MoveTo 45 87 self)
			)
			(4
				(ego setCel: 3 setMotion: MoveTo 54 104 self)
			)
			(5
				(ego setCel: 4 setMotion: MoveTo 71 122 self)
			)
			(6
				(ego setCel: 5 setMotion: MoveTo 78 128 self)
			)
			(7
				(ego
					setStep: 15 15
					moveSpeed: 1
					setLoop: 0
					setCel: 3
					setMotion: MoveTo 80 143 self
				)
			)
			(8
				(ego setCel: 4 setMotion: MoveTo 78 149 self)
			)
			(9
				(ego setCel: 8 setPri: -1 setMotion: MoveTo 78 154 self)
			)
			(10
				(soundFx number: 600 loop: 1 play:)
				(ShakeScreen 5)
				(= ticks 120)
			)
			(11
				(messager say: 0 0 4 1 self 105)
			)
			(12 (= cycles 2))
			(13
				(ego
					setLoop: 2
					cel: 0
					x: 107
					y: 154
					moveSpeed: 6
					setCycle: EndLoop self
				)
			)
			(14
				(ego
					view: 0
					loop: 8
					cel: 0
					setLoop: -1
					setLoop: (ScriptID 0 8)
					setStep: 4 3
					setCycle: StopWalk -1
					setSpeed: egoSpeed
				)
				(= seconds 2)
			)
			(15
				(theText init:)
				(= ticks 140)
			)
			(16
				(theText dispose:)
				(= ticks 30)
			)
			(17
				(ego setMotion: PolyPath 301 156 self)
			)
			(18
				(self setScript: outtaHere self)
			)
			(19 (curRoom newRoom: 130))
		)
	)
)

(class STimer of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds register))
			(1 (self dispose:))
		)
	)
)

(instance bat1 of Prop
	(properties
		x 115
		y 10
		view 600
		loop 4
		signal ignrAct
		cycleSpeed 16
	)
	
	(method (init)
		(super init:)
		(self stopUpd: setPri: 1)
	)
	
	(method (cue)
		(if (Random 0 6)
			(self setCycle: BegLoop self)
		else
			(self stopUpd:)
			(self setScript: (STimer new:) self 2)
		)
	)
)

(instance ripple_1 of Prop
	(properties
		x 279
		y 179
		view 600
		loop 7
		signal ignrAct
		cycleSpeed 10
		detailLevel 4
		name "ripple#1"
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance ripple_2 of Prop
	(properties
		x 157
		y 176
		view 600
		loop 8
		signal ignrAct
		cycleSpeed 10
		detailLevel 4
		name "ripple#2"
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance ripple_3 of Prop
	(properties
		x 55
		y 181
		view 600
		loop 9
		signal ignrAct
		cycleSpeed 10
		detailLevel 4
		name "ripple#3"
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance ripple_4 of Prop
	(properties
		x 133
		y 172
		view 600
		loop 10
		signal ignrAct
		cycleSpeed 10
		detailLevel 4
		name "ripple#4"
	)
	
	(method (init)
		(self setCycle: Forward)
		(super init:)
	)
)

(instance passPorts of View
	(properties
		x 243
		y 120
		view 600
		loop 6
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
)

(instance flier of Actor
	(properties
		x 150
		y -30
		view 600
		loop 1
		cel 1
		priority 21
		signal (| ignrAct ignrHrz fixPriOn stopUpdOn)
	)
	
	(method (cue)
		(self
			cel: 0
			setStep: 3 2
			setLoop: 1
			setCycle: Forward
			setMotion: MoveTo (Random 0 250) (Random 0 100) self
		)
	)
)

(instance fireMarshall of Actor
	(properties
		x 262
		y 115
		view 618
		signal ignrAct
	)
	
	(method (init)
		(super init:)
		(self stopUpd: setPri: 3)
	)
)

(instance fishermanBat of Actor
	(properties
		x 183
		y 138
		view 620
		loop 2
		cel 1
		priority 14
		signal (| ignrAct ignrHrz fixPriOn)
	)
	
	(method (init)
		(super init:)
		(self stopUpd: setScript: batNoisesScr)
	)
)

(instance tentBat of Actor
	(properties
		x 131
		y 103
		view 624
		signal (| ignrAct ignrHrz)
	)
	
	(method (init)
		(super init:)
		(self stopUpd:)
	)
)

(instance punkBat of Actor
	(properties
		x 129
		y 60
		view 626
		cel 3
		signal (| ignrAct ignrHrz)
	)
	
	(method (init)
		(super init:)
		(self setPri: 0 stopUpd:)
	)
)

(instance freeTailBat of Actor
	(properties
		x 205
		y 39
		view 628
		loop 2
		signal (| ignrAct ignrHrz)
	)
	
	(method (init)
		(super init:)
		(self stopUpd: addToPic:)
	)
)

(instance outtaHere of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setScale: Scaler 100 75 149 110
					signal: (| (ego signal?) notUpd)
					setMotion: MoveTo 305 141 self
				)
			)
			(1
				(ego setPri: 0 setMotion: MoveTo 273 124 self)
			)
			(2
				(ego
					setSpeed: 7
					yStep: (- (ego yStep?) 1)
					setMotion: MoveTo 241 85 self
				)
			)
			(3
				(ego
					setScale: Scaler 67 40 84 31
					setSpeed: 8
					setMotion: MoveTo 269 59 self
				)
			)
			(4
				(ego
					yStep: (- (ego yStep?) 1)
					setMotion: MoveTo 281 41 self
				)
			)
			(5
				(ego setMotion: MoveTo 243 24 self)
			)
			(6 (self dispose:))
		)
	)
)

(instance batSqueeks of Sound
	(properties
		number 602
	)
)

(instance batNoisesScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(batSqueeks dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks (Random 60 200)))
			(2
				(= state (- state 2))
				(if (== (batSqueeks prevSignal?) -1)
					(batSqueeks play:)
				else
					(self cue:)
				)
			)
		)
	)
)

(instance waterFall of Actor
	(properties
		x 65
		y 95
		view 600
		loop 11
		signal fixPriOn
	)
)

(instance theText of View
	(properties
		x 80
		y 25
		view 93
		loop 2
		priority 15
		signal fixPriOn
	)
)
