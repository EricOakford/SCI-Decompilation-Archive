;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include sci.sh)
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
	local0
)
(instance inPlane of Rm
	(properties
		picture 44
	)
	
	(method (init)
		(Load rsVIEW 44)
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
			ignoreActors: 1
			setScript: (Clone cloudScript)
		)
		(cloud2
			init:
			ignoreActors: 1
			setScript: (Clone cloudScript)
		)
		(if (== howFast 2)
			(cloud3
				init:
				ignoreActors: 1
				setScript: (Clone cloudScript)
			)
			(cloud4
				init:
				ignoreActors: 1
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
	(properties)
	
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
				(= local0
					(Display
						44
						1
						dsALIGN
						0
						dsCOORD
						20
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(1
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						2
						dsALIGN
						0
						dsCOORD
						20
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(2
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						3
						dsALIGN
						0
						dsCOORD
						20
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
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
				(== (event message?) KEY_RETURN)
				(== (event type?) evKEYBOARD)
			)
			(if local0 (Display 44 0 108 local0) (self cue:))
			(event claimed: 1)
		)
	)
)

(instance flyToHawaiiScript of Script
	(properties)
	
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
				(= local0
					(Display
						44
						4
						dsALIGN
						0
						dsCOORD
						20
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(1
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						5
						dsALIGN
						0
						dsCOORD
						10
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(2
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						6
						dsALIGN
						0
						dsCOORD
						10
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(3
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						7
						dsALIGN
						0
						dsCOORD
						30
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(4
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						8
						dsALIGN
						0
						dsCOORD
						40
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(5
				(Display 44 0 108 local0)
				(if (ego has: 0)
					(client setScript: ordersScript)
				else
					(= local0
						(Display
							44
							9
							dsALIGN
							0
							dsCOORD
							40
							10
							dsWIDTH
							300
							dsCOLOR
							15
							dsSAVEPIXELS
						)
					)
				)
				(= seconds 10)
			)
			(6
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						10
						dsALIGN
						0
						dsCOORD
						40
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(7
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						11
						dsALIGN
						0
						dsCOORD
						40
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(8
				(Display 44 0 108 local0)
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
				(== (event message?) KEY_RETURN)
				(== (event type?) evKEYBOARD)
			)
			(if local0 (Display 44 0 108 local0) (self cue:))
			(event claimed: 1)
		)
	)
)

(instance ordersScript of Script
	(properties)
	
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
				(= local0
					(Display
						44
						12
						dsALIGN
						0
						dsCOORD
						40
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(2
				(hand setMotion: MoveTo 169 172 self)
			)
			(3
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						13
						dsALIGN
						0
						dsCOORD
						40
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(4
				(Display 44 0 108 local0)
				(= local0
					(Display
						44
						11
						dsALIGN
						0
						dsCOORD
						40
						10
						dsWIDTH
						300
						dsCOLOR
						15
						dsSAVEPIXELS
					)
				)
				(= seconds 10)
			)
			(5
				(Display 44 0 108 local0)
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
		(or (!= (event type?) evKEYBOARD) (event claimed?))
			(return)
		)
		(if local0 (Display 44 0 108 local0) (self cue:))
		(event claimed: 1)
	)
)

(instance cloudScript of Script
	(properties)
	
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

(instance head of PV
	(properties
		y 189
		x 110
		view 44
		priority 14
	)
)

(instance hand of Act
	(properties
		y 172
		x 169
		view 44
		cel 1
	)
)

(instance cloud1 of Act
	(properties
		view 44
	)
)

(instance cloud2 of Act
	(properties
		view 44
	)
)

(instance cloud3 of Act
	(properties
		view 44
	)
)

(instance cloud4 of Act
	(properties
		view 44
	)
)
