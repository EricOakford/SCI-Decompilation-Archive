;;; Sierra Script 1.0 - (do not remove this comment)
(script# 56)
(include sci.sh)
(use Main)
(use HighLite)
(use Intrface)
(use Motion)
(use Game)
(use System)

(public
	Room56 0
)
(synonyms
	(stair upstair)
)

(instance glow of HighLite
	(properties)
)

(instance Room56 of Rm
	(properties
		picture 56
	)
	
	(method (init)
		(= horizon 0)
		(= south (= east 55))
		(= global189 52)
		(super init:)
		(self setRegions: 242)
		(ego view: 7 init:)
		(if (== prevRoomNum 55)
			(ego loop: 1 posn: 307 188)
		else
			(self setScript: GoingDown)
		)
		(glow deltaX: 8 deltaY: 8 init:)
	)
	
	(method (doit)
		(if (FirstEntry) (Print 56 0))
		(if
		(and (& (ego onControl:) $0002) (== script 0))
			(self setScript: GoingUp)
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
		(if (== (event type?) evSAID)
			(cond 
				((Said 'examine[<around,at][/room]') (Print 56 0))
				((Said 'examine/passage') (Print 56 1))
				(
				(or (Said 'examine/stair,stair') (Said 'examine<up')) (Print 56 2))
			)
		)
	)
	
	(method (newRoom n)
		(super newRoom: n)
	)
)

(instance GoingUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					setLoop: 1
					setMotion: MoveTo 177 122 self
				)
			)
			(1
				(if (& [global148 3] $0200)
					(ego loop: 2)
					(= state 3)
					(= cycles 2)
				else
					(ego
						view: 26
						loop: 0
						cel: 0
						setMotion: MoveTo 173 116 self
					)
				)
			)
			(2
				(ego loop: 1 cel: 0 cycleSpeed: 1 setCycle: End self)
			)
			(3
				(ego loop: 2 cel: 0 setCycle: End self)
			)
			(4
				(HandsOn)
				(ego setLoop: -1 cycleSpeed: -1 illegalBits: -32768)
				(client setScript: 0)
				(curRoom newRoom: 57)
			)
		)
	)
)

(instance GoingDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					posn: 178 116
					illegalBits: 0
					setLoop: 0
					setMotion: MoveTo 238 185 self
				)
			)
			(1
				(HandsOn)
				(ego setLoop: -1 illegalBits: -32768)
				(client setScript: 0)
			)
		)
	)
)
