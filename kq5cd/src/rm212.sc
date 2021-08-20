;;; Sierra Script 1.0 - (do not remove this comment)
(script# 212)
(include game.sh)
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
	thirstCount
	thirstWarning
	pts1 = [
		0 0
		319 0
		319 112
		213 117
		193 121
		164 117
		145 118
		121 113
		77 116
		57 120
		17 109
		0 112
		]
)
(procedure (InitDesertRoom)
	(ego setMotion: 0 setCycle: 0)
	(= globalCedric (++ thirstCount))
	(curRoom drawPic: 212)
	(addToPics dispose:)
	(switch (mod desertRoomX 4)
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
				(= thirstCount globalCedric)
			)
			(14
				(= desertRoomX 1)
				(= desertRoomY 1)
				(= thirstCount 1)
			)
			(else 
				(= thirstCount globalCedric)
			)
		)
		(ego
			view: 0
			setPri: -1
			illegalBits: cWHITE
			setStep: 3 2
			init:
		)
		(poly1 points: @pts1 size: 12)
		(self addObstacle: poly1)
		(theMusic2 number: 3 loop: -1 play: 112)
		(theMusic number: 2 loop: -1 play: 112)
	)
	
	(method (doit &tmp edge)
		(cond 
			(script (script doit:))
			((and (== thirstCount 5) (not thirstWarning))
				(= thirstWarning TRUE)
				(SpeakAudio 315)
			)
			((> thirstCount 6)
				(self setScript: dying)
			)
			((= edge (ego edgeHit?))
				(switch edge
					(SOUTH
						(++ desertRoomY)
						(curRoom newRoom: 15)
						(= globalCedric thirstCount)
					)
					(EAST
						(cond 
							((== (-- desertRoomX) 7)
								(curRoom newRoom: 213)
								(theMusic2 fade:)
								(theMusic fade:)
							)
							((== desertRoomX 0)
								(= desertRoomX 0)
								(= desertRoomY 1)
								(curRoom newRoom: 14)
							)
							(else (InitDesertRoom)
								(ego setCycle: KQ5SyncWalk)
							)
						)
					)
					(WEST
						(if (== (++ desertRoomX) 7)
							(theMusic2 fade:)
							(theMusic fade:)
							(curRoom newRoom: 213)
						else
							(InitDesertRoom)
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
	
	(method (changeState newState &tmp toX temp1)
		(switch (= state newState)
			(0
				(= cycles 5)
			)
			(1
				(theMusic2 number: 785 loop: 1 play:)
				(User canControl: FALSE canInput: FALSE)
				(ego setPri: 14 setMotion: PolyPath 163 160 self)
			)
			(2
				(SpeakAudio 316 self)
				(ego view: 358 cycleSpeed: 2 normal: 0 setCycle: EndLoop self)
				((ego head?) hide:)
			)
			(3
				(switch (ego loop?)
					(0 (= toX 25))
					(else  (= toX 60))
				)
				(buzzard2 init: setScript: dying2)
				(buzzard
					init:
					setLoop: 6
					setCycle: Walk
					setMotion: MoveTo (- (ego x?) toX) (ego y?) self
				)
			)
			(4
				(buzzard setLoop: 4 cel: 4 setCycle: BegLoop self)
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
				(EgoDead 264 0 EndLoop 20)
			)
		)
	)
)

(instance dying2 of Script

	(method (changeState newState &tmp temp0 toX)
		(switch (= state newState)
			(0
				(switch (ego loop?)
					(0 (= toX 60))
					(else  (= toX 25))
				)
				(buzzard2
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) toX) (ego y?) self
				)
			)
			(1
				(buzzard2 setLoop: 5 cel: 4 setCycle: BegLoop self)
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
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 764)
					(event claimed: TRUE)
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
				(not (== (event type?) userEvent))
			)
			(return)
		else
			(switch (event message?)
				(verbLook
					(SpeakAudio 765)
					(event claimed: TRUE)
				)
				(verbDo
					(SpeakAudio 766)
					(event claimed: TRUE)
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
		signal fixPriOn
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
		signal fixPriOn
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
		type PBarredAccess
	)
)
