;;; Sierra Script 1.0 - (do not remove this comment)
(script# 55)
(include sci.sh)
(use Main)
(use HighLite)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room55 0
)
(synonyms
	(room room passage)
)

(local
	[local0 80] = [2 35 131 5 3 72 128 5 1 80 132 5 2 87 129 5 3 140 130 5 1 187 130 5 2 242 131 5 1 270 132 5 0 80 132 4 0 254 132 4 1 42 130 4 3 83 131 5 2 89 131 5 2 143 130 5 1 175 132 5 3 208 129 5 2 270 132 5 0 56 132 4 0 160 132 4 0 255 132 4]
	local80 =  1
	[local81 2]
)
(instance Room55 of Rm
	(properties
		picture 55
	)
	
	(method (init &tmp [theRock1 10] temp10 temp11)
		(= horizon 0)
		(if (== global189 51)
			(= east 51)
			(= west 52)
		else
			(= east 52)
			(= west 56)
		)
		(super init:)
		(self setRegions: 242)
		(moodMusic play:)
		(if
			(or
				(and (== prevRoomNum 52) (== global189 51))
				(== prevRoomNum 56)
			)
			(ego x: 1 setPri: -1)
		else
			(ego x: 310)
		)
		(ego y: 136 view: 15 xStep: 2 init:)
		(eyes
			illegalBits: 0
			ignoreActors: 1
			setScript: ShowEyes
			setPri: 15
			setCycle: Fwd
			init:
			hide:
		)
		(glow deltaX: 6 deltaY: 2 eyesID: eyes init:)
		(= [theRock1 0] rock1)
		(= [theRock1 1] rock2)
		(= [theRock1 2] rock3)
		(= [theRock1 3] rock4)
		(= [theRock1 4] rock5)
		(= [theRock1 5] rock6)
		(= [theRock1 6] rock7)
		(= [theRock1 7] timber1)
		(= [theRock1 8] timber2)
		(= [theRock1 9] rockTim)
		(if (== global189 51) (= temp11 -1) else (= temp11 39))
		(= temp10 0)
		(while (< temp10 10)
			([theRock1 temp10]
				view: 155
				loop: [local0 (++ temp11)]
				posn: [local0 (++ temp11)] [local0 (++ temp11)]
				setPri: [local0 (++ temp11)]
				init:
				stopUpd:
				hide:
			)
			(++ temp10)
		)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 55 0))
		(if
			(eyes
				inRect: (glow nsLeft?) (glow nsTop?) (glow nsRight?) (glow nsBottom?)
			)
			(eyes hide:)
		)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript 214)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return))
		(super handleEvent: event)
		(if
			(and
				(== (event type?) evSAID)
				(Said 'examine[<around,at][/room]')
			)
			(Print 55 0)
		)
	)
	
	(method (newRoom n)
		(ego xStep: 3)
		(super newRoom: n)
	)
)

(instance ShowEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if local80
					(eyes
						loop: (Random 4 6)
						cel: 0
						posn: (Random 10 310) (Random 95 134)
					)
					(if
						(not
							(eyes
								inRect: (glow nsLeft?) (glow nsTop?) (glow nsRight?) (glow nsBottom?)
							)
						)
						(eyes show:)
						(= local80 (^ local80 $0001))
						(= seconds (Random 2 3))
					else
						(= cycles 1)
					)
				else
					(eyes hide:)
					(= local80 (^ local80 $0001))
					(= seconds (Random 1 2))
				)
				(= state -1)
			)
		)
	)
)

(instance glow of HighLite
	(properties)
)

(instance rock1 of Prop
	(properties)
)

(instance rock2 of Prop
	(properties)
)

(instance rock3 of Prop
	(properties)
)

(instance rock4 of Prop
	(properties)
)

(instance rock5 of Prop
	(properties)
)

(instance rock6 of Prop
	(properties)
)

(instance rock7 of Prop
	(properties)
)

(instance timber1 of Prop
	(properties)
)

(instance timber2 of Prop
	(properties)
)

(instance rockTim of Prop
	(properties)
)

(instance moodMusic of Sound
	(properties
		number 61
		loop -1
	)
)

(instance eyes of Act
	(properties
		view 155
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(cond 
			((Said '/mouse>')
				(cond 
					((Said 'examine') (Print 55 1))
					((Said 'get,capture') (Print 55 2))
					((Said 'kill') (Print 55 3))
				)
			)
			((MousedOn self event 3) (event claimed: 1) (ParseName {eye}))
		)
	)
)
