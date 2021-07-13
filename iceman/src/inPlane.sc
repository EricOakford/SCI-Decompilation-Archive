;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include game.sh)
(use Main)
(use NormalEgo)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	inPlane 0
)

(local
	saveBits
)
(instance inPlane of Room
	(properties
		picture 44
	)
	
	(method (init)
		(Load VIEW 44)
		(super init:)
		(HandsOff)
		(globalSound
			number: (SoundFX 83)
			owner: theGame
			priority: 1
			loop: -1
			play:
		)
		(addToPics add: head doit:)
		(cloud1
			init:
			ignoreActors: TRUE
			setScript: (Clone cloudScript)
		)
		(cloud2
			init:
			ignoreActors: TRUE
			setScript: (Clone cloudScript)
		)
		(if (== howFast fast)
			(cloud3
				init:
				ignoreActors: TRUE
				setScript: (Clone cloudScript)
			)
			(cloud4
				init:
				ignoreActors: TRUE
				setScript: (Clone cloudScript)
			)
		)
		(switch prevRoomNum
			(1
				(self setScript: flyToWashingtonScript)
			)
			(18
				(self setScript: flyToHawaiiScript)
			)
		)
	)
)

(instance flyToWashingtonScript of Script

	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(cls)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits
					(Display 44 1
						p_mode teJustLeft
						p_at 20 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(1
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 2
						p_mode teJustLeft
						p_at 20 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(2
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 3
						p_mode teJustLeft
						p_at 20 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(3
				(HandsOn)
				(curRoom newRoom: 17)
			)
		)
	)
	
	(method (cue)
		(= cycles (= seconds 0))
		(super cue:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(if saveBits
				(Display 44 0 p_restore saveBits)
				(self cue:)
			)
			(event claimed: TRUE)
		)
	)
)

(instance flyToHawaiiScript of Script
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(cls)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits
					(Display 44 4
						p_mode teJustLeft
						p_at 20 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(1
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 5
						p_mode teJustLeft
						p_at 10 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(2
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 6
						p_mode teJustLeft
						p_at 10 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(3
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 7
						p_mode teJustLeft
						p_at 30 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(4
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 8
						p_mode teJustLeft
						p_at 40 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(5
				(Display 44 0 p_restore saveBits)
				(if (ego has: iEnvelope)
					(client setScript: ordersScript)
				else
					(= saveBits
						(Display 44 9
							p_mode teJustLeft
							p_at 40 10
							p_width 300
							p_color vWHITE
							p_save
						)
					)
				)
				(= seconds 10)
			)
			(6
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 10
						p_mode teJustLeft
						p_at 40 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(7
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 11
						p_mode teJustLeft
						p_at 40 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(8
				(Display 44 0 p_restore saveBits)
				(HandsOn)
				(NormalEgo 204)
				(curRoom newRoom: 22)
				(globalSound fade:)
			)
		)
	)
	
	(method (cue)
		(= cycles (= seconds 0))
		(super cue:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(== (event message?) ENTER)
				(== (event type?) keyDown)
			)
			(if saveBits
				(Display 44 0 p_restore saveBits)
				(self cue:)
			)
			(event claimed: TRUE)
		)
	)
)

(instance ordersScript of Script
	
	(method (init)
		(super init: &rest)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(cls)
		(keyDownHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(hand
					init:
					setLoop: 0
					setCel: 1
					setMotion: MoveTo 155 158 self
				)
			)
			(1
				(= saveBits
					(Display 44 12
						p_mode teJustLeft
						p_at 40 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(2
				(hand setMotion: MoveTo 169 172 self)
			)
			(3
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 13
						p_mode teJustLeft
						p_at 40 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(4
				(Display 44 0 p_restore saveBits)
				(= saveBits
					(Display 44 11
						p_mode teJustLeft
						p_at 40 10
						p_width 300
						p_color vWHITE
						p_save
					)
				)
				(= seconds 10)
			)
			(5
				(Display 44 0 p_restore saveBits)
				(HandsOn)
				(NormalEgo 204)
				(curRoom newRoom: 22)
				(globalSound fade:)
			)
		)
	)
	
	(method (cue)
		(= cycles (= seconds 0))
		(super cue:)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) keyDown) (event claimed?))
			(return)
		)
		(if saveBits
			(Display 44 0 p_restore saveBits)
			(self cue:)
		)
		(event claimed: TRUE)
	)
)

(instance cloudScript of Script
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0 (= seconds (Random 3 10)))
			(1
				(switch (Random 0 3)
					(0
						(= temp0 2)
						(= temp1 1)
						(= temp2 71)
					)
					(1
						(= temp0 4)
						(= temp1 2)
						(= temp2 81)
					)
					(2
						(= temp0 8)
						(= temp1 3)
						(= temp2 92)
					)
					(3
						(= temp0 16)
						(= temp1 4)
						(= temp2 107)
					)
				)
				(client
					posn: 290 temp2
					setLoop: 1
					setCel: (Random 0 1)
					setPri: temp1
					setStep: temp0 1
					setMotion: MoveTo -10 temp2 self
				)
			)
			(2 (self init:))
		)
	)
)

(instance head of PicView
	(properties
		y 189
		x 110
		view 44
		priority 14
	)
)

(instance hand of Actor
	(properties
		y 172
		x 169
		view 44
		cel 1
	)
)

(instance cloud1 of Actor
	(properties
		view 44
	)
)

(instance cloud2 of Actor
	(properties
		view 44
	)
)

(instance cloud3 of Actor
	(properties
		view 44
	)
)

(instance cloud4 of Actor
	(properties
		view 44
	)
)
