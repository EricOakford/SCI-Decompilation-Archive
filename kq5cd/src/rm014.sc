;;; Sierra Script 1.0 - (do not remove this comment)
(script# 14)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use Polygon)
(use RFeature)
(use Motion)
(use Actor)
(use System)

(public
	rm014 0
)

(local
	local0
	gEgoEdgeHit
	local2
	[local3 32] = [0 1 32 109 0 6 241 145 1 0 222 113 1 4 67 179 0 1 62 109 0 6 141 145 1 0 122 113 1 4 167 179]
	[local35 8] = [144 178 168 172 194 178 154 180]
	[local43 10] = [108 137 146 135 182 138 187 146 105 146]
	[local53 12] = [146 105 156 111 140 114 56 111 22 106 60 99]
	[local65 8] = [86 178 70 180 47 178 68 172]
	[local73 10] = [243 133 281 137 283 145 207 145 211 136]
	[local83 8] = [234 104 237 112 186 111 191 105]
	[local91 10] = [0 101 65 101 67 106 37 111 0 109]
	[local101 24] = [0 0 319 0 319 125 283 117 239 121 226 115 208 115 181 110 154 114 159 120 82 124 0 123]
)
(procedure (localproc_000e param1 param2 param3)
	(if (and (< param1 param2) (< param2 param3))
		(if (< param2 (/ (+ param1 param3) 2))
			(= param2 param1)
		else
			(= param2 param3)
		)
	)
	(return param2)
)

(procedure (localproc_003a param1 param2 param3)
	(if (< param2 param1) (= param2 param1))
	(if (> param2 param3) (= param2 param3))
	(return param2)
)

(procedure (localproc_0702 &tmp temp0)
	(ego setMotion: 0 setCycle: 0)
	(RedrawCast)
	(addToPics dispose:)
	(features eachElementDo: #dispose release:)
	(if (IsObject roadRunner) (roadRunner dispose:))
	(curRoom drawPic: 14)
	(switch gEgoEdgeHit
		(1
			(ego posn: (ego x?) 186)
			(-- global315)
		)
		(else 
			(ego
				posn: (localproc_003a
					70
					(localproc_000e 183 (ego y?) 255)
					320
				) 109
			)
			(++ global315)
		)
	)
	(hills1 view: 352 loop: 2 cel: 0 x: 122 y: 47 signal: 1)
	(switch global315
		(1
			(curRoom west: 212)
			(curRoom east: 13)
			(curRoom south: 0)
			(= temp0 100)
			(hills1
				view: 349
				loop: 0
				cel: 0
				x: 80
				y: 122
				signal: 16384
			)
			(hills2
				view: 349
				loop: 0
				cel: 1
				x: 232
				y: 120
				signal: 16384
			)
			(trees priority: 1 x: 264)
			(addToPics add: trees hills1 hills2 doit:)
			(curRoom setFeatures: hills1 hills2 thatRoom thisRoom)
			(curRoom obstacles: polyList13)
		)
		(2
			(trees priority: 3 x: 234)
			(curRoom east: 12)
			(curRoom west: 15)
			(curRoom south: 0)
			(= temp0 0)
			(addToPics add: hills1)
			(curRoom setFeatures: hills1 thisRoom)
			(curRoom obstacles: polyList12)
		)
		(3
			(trees x: 294)
			(curRoom east: 11)
			(curRoom west: 15)
			(curRoom south: 211)
			(= temp0 16)
			(curRoom setFeatures: thisRoom)
			(curRoom obstacles: polyList11)
		)
	)
	(if (!= temp0 100)
		(rock1
			loop: [local3 temp0]
			cel: [local3 (+ temp0 1)]
			x: [local3 (+ temp0 2)]
			y: [local3 (+ temp0 3)]
		)
		(rock2
			loop: [local3 (+ temp0 4)]
			cel: [local3 (+ temp0 5)]
			x: [local3 (+ temp0 6)]
			y: [local3 (+ temp0 7)]
		)
		(rock3
			loop: [local3 (+ temp0 8)]
			cel: [local3 (+ temp0 9)]
			x: [local3 (+ temp0 10)]
			y: [local3 (+ temp0 11)]
		)
		(rock4
			loop: [local3 (+ temp0 12)]
			cel: [local3 (+ temp0 13)]
			x: [local3 (+ temp0 14)]
			y: [local3 (+ temp0 15)]
		)
		(addToPics add: trees rock1 rock2 rock3 rock4 doit:)
	)
	(ego forceUpd:)
	((ego head?)
		x: (ego x?)
		y: (ego y?)
		z: (CelHigh (ego view?) (ego loop?) (ego cel?))
	)
	(= gEgoEdgeHit 0)
	(ego edgeHit: 0)
	(= local0 0)
	(switch gEgoEdgeHit
		(1 (ego loop: 11 cel: 3))
		(else  (ego loop: 11 cel: 2))
	)
)

(instance rm014 of KQ5Room
	(properties
		picture 14
		horizon 90
		west 15
	)
	
	(method (init &tmp temp0)
		(super init:)
		(cond 
			(
				(or
					(== prevRoomNum 13)
					(and
						(== global315 1)
						(or (== prevRoomNum 15) (== prevRoomNum 212))
					)
				)
				(= global315 1)
				(= west 212)
				(= east 13)
				(= temp0 100)
				(hills1
					view: 349
					loop: 0
					cel: 0
					x: 80
					y: 122
					signal: 16384
				)
				(hills2
					view: 349
					loop: 0
					cel: 1
					x: 232
					y: 120
					signal: 16384
				)
				(trees priority: 1 x: 264 signal: 16384)
				(self setFeatures: hills1 hills2 thatRoom thisRoom)
				(addToPics add: trees hills1 hills2 doit:)
				(curRoom obstacles: polyList13)
			)
			(
				(or
					(== prevRoomNum 12)
					(and (== global315 2) (== prevRoomNum 15))
				)
				(= global315 2)
				(= east 12)
				(= temp0 0)
				(addToPics add: hills1)
				(curRoom obstacles: polyList12)
				(self setFeatures: thisRoom)
			)
			(
				(or
					(== prevRoomNum 11)
					(and (== global315 3) (== prevRoomNum 15))
				)
				(trees x: 294)
				(= global315 3)
				(= east 11)
				(= south 211)
				(= temp0 16)
				(curRoom obstacles: polyList11)
				(self setFeatures: thisRoom)
			)
		)
		(if (!= temp0 100)
			(rock1
				loop: [local3 temp0]
				cel: [local3 (+ temp0 1)]
				x: [local3 (+ temp0 2)]
				y: [local3 (+ temp0 3)]
			)
			(rock2
				loop: [local3 (+ temp0 4)]
				cel: [local3 (+ temp0 5)]
				x: [local3 (+ temp0 6)]
				y: [local3 (+ temp0 7)]
			)
			(rock3
				loop: [local3 (+ temp0 8)]
				cel: [local3 (+ temp0 9)]
				x: [local3 (+ temp0 10)]
				y: [local3 (+ temp0 11)]
			)
			(rock4
				loop: [local3 (+ temp0 12)]
				cel: [local3 (+ temp0 13)]
				x: [local3 (+ temp0 14)]
				y: [local3 (+ temp0 15)]
			)
			(addToPics add: trees rock1 rock2 rock3 rock4 doit:)
		)
		(ego view: 0 y: 150 setPri: -1 ignoreHorizon: 1 init:)
		(poly1 points: @local35 size: 4)
		(poly2 points: @local43 size: 5)
		(poly3 points: @local53 size: 6)
		(poly4 points: @local65 size: 4)
		(poly5 points: @local73 size: 5)
		(poly6 points: @local83 size: 4)
		(poly7 points: @local91 size: 5)
		(poly8 points: @local101 size: 12)
		(polyList11 add: poly1 poly2 poly3)
		(polyList12 add: poly4 poly5 poly6 poly7)
		(polyList13 add: poly8)
		(theMusic
			number: 24
			loop: -1
			play: (if (< (ego x?) 160) 40 else 112)
		)
		(theMusic2
			number: 3
			loop: -1
			play: (if (< (ego x?) 160) 112 else 20)
		)
		(theMusic3
			number: 2
			loop: -1
			play: (if (< (ego x?) 160) 112 else 20)
		)
	)
	
	(method (doit)
		(cond 
			((< (ego x?) 120)
				(theMusic fade: 40 1 1 0)
				(theMusic2 fade: 112 1 1 0)
				(theMusic3 fade: 112 1 1 0)
			)
			((> (ego x?) 240)
				(theMusic fade: 112 1 1 0)
				(theMusic2 fade: 20 1 1 0)
				(theMusic3 fade: 20 1 1 0)
			)
			(else
				(theMusic fade: 80 1 1 0)
				(theMusic2 fade: 80 1 1 0)
				(theMusic3 fade: 80 1 1 0)
			)
		)
		(cond 
			(script (script doit:))
			(
			(and (not local0) (= gEgoEdgeHit (ego edgeHit?)))
				(if (== prevRoomNum east) (= globalCedric 0))
				(theGame setCursor: waitCursor 1)
				(= local0 1)
				(switch gEgoEdgeHit
					(1
						(localproc_0702)
						(ego setCycle: KQ5SyncWalk)
					)
					(3
						(if south
							(curRoom newRoom: south)
						else
							(localproc_0702)
							(ego setCycle: KQ5SyncWalk)
						)
					)
					(2
						(theMusic2 fade:)
						(theMusic3 fade:)
						(curRoom newRoom: east)
					)
					(4
						(theMusic fade:)
						(= global314 1)
						(curRoom newRoom: west)
					)
				)
				(theGame setCursor: ((theIconBar curIcon?) cursor?) 1)
			)
			(
				(and
					(not (Random 0 100))
					(== (curRoom obstacles?) polyList12)
					(> 250 (ego x?))
					(> (ego x?) 200)
					(not local2)
					(not (roadRunner script?))
				)
				(= local2 1)
				(run register: (if (< (ego y?) 130) 165 else 95))
				(roadRunner init: setScript: run)
			)
		)
	)
	
	(method (dispose)
		(polyList11 dispose:)
		(polyList12 dispose:)
		(polyList13 dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance run of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(roadRunner
					x: -20
					y: register
					setLoop: 10
					setCycle: Fwd
					cycleSpeed: 0
					setMotion: MoveTo 340 register self
				)
			)
			(1
				(roadRunner dispose:)
				(self dispose:)
			)
		)
	)
)

(instance thisRoom of RFeature
	(properties
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 298)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance thatRoom of RFeature
	(properties)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (& (OnControl 4 (event x?) (event y?)) $0002))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 299)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance trees of RPicView
	(properties
		x 234
		y 53
		view 352
		cel 3
		priority 3
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 300)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance hills1 of RPicView
	(properties
		x 122
		y 47
		view 352
		loop 2
		priority 2
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(switch (curRoom east?)
						(13 (SpeakAudio 301))
						(12 (SpeakAudio 302))
					)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(if (== (curRoom east?) 13)
						(SpeakAudio 766)
						(event claimed: 1)
					)
				)
			)
		)
	)
)

(instance hills2 of RPicView
	(properties
		x 197
		y 49
		view 352
		loop 2
		cel 1
		priority 2
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
				(& (OnControl 4 (event x?) (event y?)) $0002)
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 301)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 766)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance rock1 of RPicView
	(properties
		x 50
		y 112
		view 352
		signal $4000
	)
)

(instance rock2 of RPicView
	(properties
		x 126
		y 105
		view 352
		cel 1
		signal $4000
	)
)

(instance rock3 of RPicView
	(properties
		x 177
		y 107
		view 352
		loop 1
		signal $4000
	)
)

(instance rock4 of RPicView
	(properties
		x 227
		y 104
		view 352
		loop 1
		cel 6
		signal $4000
	)
)

(instance roadRunner of Actor
	(properties
		view 352
		signal $6000
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (== (event type?) 16384))
				(not (MousedOn self event))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 303)
					(event claimed: 1)
				)
				(JOY_RIGHT
					(SpeakAudio 9118)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance bush1 of Prop
	(properties)
)

(instance polyList11 of List
	(properties)
)

(instance polyList12 of List
	(properties)
)

(instance polyList13 of List
	(properties)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)

(instance poly2 of Polygon
	(properties
		type $0002
	)
)

(instance poly3 of Polygon
	(properties
		type $0002
	)
)

(instance poly4 of Polygon
	(properties
		type $0002
	)
)

(instance poly5 of Polygon
	(properties
		type $0002
	)
)

(instance poly6 of Polygon
	(properties
		type $0002
	)
)

(instance poly7 of Polygon
	(properties
		type $0002
	)
)

(instance poly8 of Polygon
	(properties
		type $0002
	)
)
