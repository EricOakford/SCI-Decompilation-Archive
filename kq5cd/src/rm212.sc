;;; Sierra Script 1.0 - (do not remove this comment)
(script# 212)
(include sci.sh)
(use Main)
(use Intrface)
(use KQ5Room)
(use PolyPath)
(use Polygon)
(use RFeature)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm212 0
)

(local
	local0
	theGPolyList15
	local2
	[local3 24] = [0 0 319 0 319 112 213 117 193 121 164 117 145 118 121 113 77 116 57 120 17 109 0 112]
)
(procedure (localproc_04b8)
	(ego setMotion: 0 setCycle: 0)
	(= globalCedric (++ theGPolyList15))
	(curRoom drawPic: 212)
	(addToPics dispose:)
	(switch (mod global314 4)
		(0
			(addToPics add: cliff1 doit:)
		)
		(1
			(addToPics add: cliff2 doit:)
		)
		(2
			(addToPics add: cliff3 doit:)
		)
		(else 
			(addToPics add: cliff4 doit:)
		)
	)
	(ego posn: (Abs (- (ego x?) 310)) (ego y?))
	((ego head?)
		x: (ego x?)
		y: (ego y?)
		z: (CelHigh (ego view?) (ego loop?) (ego cel?))
	)
	(RedrawCast)
	(ego edgeHit: 0)
	(= local0 0)
)

(instance rm212 of KQ5Room
	(properties
		picture 212
	)
	
	(method (init)
		(super init:)
		(self setFeatures: cliffs room)
		(switch prevRoomNum
			(15
				(ego posn: (ego x?) 187)
				(= theGPolyList15 globalCedric)
			)
			(14
				(= global314 1)
				(= global315 1)
				(= theGPolyList15 1)
			)
			(else 
				(= theGPolyList15 globalCedric)
			)
		)
		(ego
			view: 0
			setPri: -1
			illegalBits: -32768
			setStep: 3 2
			init:
		)
		(poly1 points: @local3 size: 12)
		(self addObstacle: poly1)
		(theMusic2 number: 3 loop: -1 play: 112)
		(theMusic number: 2 loop: -1 play: 112)
	)
	
	(method (doit &tmp egoEdgeHit)
		(cond 
			(script (script doit:))
			((and (== theGPolyList15 5) (not local2)) (= local2 1) (SpeakAudio 315))
			((> theGPolyList15 6) (self setScript: dying))
			((= egoEdgeHit (ego edgeHit?))
				(switch egoEdgeHit
					(3
						(++ global315)
						(curRoom newRoom: 15)
						(= globalCedric theGPolyList15)
					)
					(2
						(cond 
							((== (-- global314) 7)
								(curRoom newRoom: 213)
								(theMusic2 fade:)
								(theMusic fade:)
							)
							((== global314 0) (= global314 0) (= global315 1) (curRoom newRoom: 14))
							(else (localproc_04b8) (ego setCycle: KQ5SyncWalk))
						)
					)
					(4
						(if (== (++ global314) 7)
							(theMusic2 fade:)
							(theMusic fade:)
							(curRoom newRoom: 213)
						else
							(localproc_04b8)
							(ego setCycle: KQ5SyncWalk)
						)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((event claimed?) (return))
			(script (return))
		)
	)
)

(instance dying of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0 (= cycles 5))
			(1
				(theMusic2 number: 785 loop: 1 play:)
				(User canControl: 0 canInput: 0)
				(ego setPri: 14 setMotion: PolyPath 163 160 self)
			)
			(2
				(SpeakAudio 316 self)
				(ego view: 358 cycleSpeed: 2 normal: 0 setCycle: End self)
				((ego head?) hide:)
			)
			(3
				(switch (ego loop?)
					(0 (= temp0 25))
					(else  (= temp0 60))
				)
				(buzzard2 init: setScript: dying2)
				(buzzard
					init:
					setLoop: 6
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) temp0) (ego y?) self
				)
			)
			(4
				(buzzard setLoop: 4 cel: 4 setCycle: Beg self)
			)
			(5
				(theMusic fade:)
				(theMusic2 fade:)
				(buzzard setLoop: 0 cel: 0)
				(= seconds 3)
			)
			(6)
			(7
				(cls)
				(= deathMessage 317)
				(EgoDead 264 0 End 20)
			)
		)
	)
)

(instance dying2 of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(switch (ego loop?)
					(0 (= temp1 60))
					(else  (= temp1 25))
				)
				(buzzard2
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) temp1) (ego y?) self
				)
			)
			(1
				(buzzard2 setLoop: 5 cel: 4 setCycle: Beg self)
			)
			(2 (buzzard2 setLoop: 1 cel: 0))
		)
	)
)

(instance room of RFeature
	(properties
		nsTop 115
		nsBottom 200
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 764)
					(event claimed: 1)
				)
			)
		)
	)
)

(instance cliffs of RFeature
	(properties
		nsBottom 115
		nsRight 320
	)
	
	(method (handleEvent event)
		(if
			(or
				(event claimed?)
				(not (MousedOn self event))
				(not (== (event type?) 16384))
			)
			(return)
		else
			(switch (event message?)
				(JOY_UPRIGHT
					(SpeakAudio 765)
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

(instance buzzard of Actor
	(properties
		x -17
		y 48
		view 360
		priority 15
		signal $0010
		cycleSpeed 2
		illegalBits $0000
	)
)

(instance buzzard2 of Actor
	(properties
		x 337
		y 74
		view 360
		priority 15
		signal $0010
		cycleSpeed 2
		illegalBits $0000
	)
)

(instance cliff1 of PicView
	(properties
		x 55
		y 113
		view 351
		priority 1
	)
)

(instance cliff2 of PicView
	(properties
		x 143
		y 115
		view 351
		cel 1
		priority 1
	)
)

(instance cliff3 of PicView
	(properties
		x 220
		y 106
		view 351
		loop 1
		priority 1
	)
)

(instance cliff4 of PicView
	(properties
		x 288
		y 105
		view 351
		loop 1
		cel 1
		priority 1
	)
)

(instance poly1 of Polygon
	(properties
		type $0002
	)
)
