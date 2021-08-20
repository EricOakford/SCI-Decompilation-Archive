;;; Sierra Script 1.0 - (do not remove this comment)
(script# 25)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm25 0
)

(local
	local0
	saveBits
	local2
)
(instance rm25 of Room
	(properties
		picture 25
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: TRUE canControl: TRUE)
		(Load VIEW 38)
		(eyes init: setScript: BoredEyes)
		(mouth init: setScript: BoredMouth)
		(super init:)
		(addToPics add: sine)
		(addToPics doit:)
		(Display 25 0
			p_at 121 183
			p_font 600
			p_width 90
			p_color vBLACK
		)
		(switch prevRoomNum
			(29
				(curRoom setScript: Welcome)
			)
			(280
				(if (> monolithBurgerBill 0) (curRoom setScript: PayUp))
			)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (handleEvent event &tmp [temp0 50])
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(or
							(Said 'pay[/dinner,alien,man,clerk,him,bill]')
							(Said 'give/bill')
						)
						(cond 
							(
							(or (!= prevRoomNum 280) (not monolithBurgerBill)) (Print 25 1))
							((>= buckazoids monolithBurgerBill)
								(if (not (-= buckazoids monolithBurgerBill))
									(ego put: iBuckazoids -1)
								)
								(= global247 1)
								(= monolithBurgerBill 0)
								(= global248 1)
								(if mealHasDecoderRing
									(theGame changeScore: 10)
								)
								(= saveDisabled FALSE)
								(curRoom setScript: LeaveRoom)
							)
							(else (curRoom setScript: noMoney))
						)
					)
					((Said 'give,trade/crystal[/dinner]') (Print 25 2))
					((or (Said 'disembark[/area]') (Said '/bye'))
						(= monolithBurgerBill 0)
						(= global248 0)
						(= saveDisabled FALSE)
						(= orderedBigBelcherCombo FALSE)
						(curRoom newRoom: 29)
					)
					((Said 'look[/area]') (Print 25 3))
					((Said 'look/up,down,deck,ceiling') (Print 25 4))
					((Said 'look,read/menace') (Print 25 5))
					((Said 'look/attire,cap') (Print 25 6))
					((Said '/bitch,her') (Print 25 7))
					(
					(Said 'look/man,clerk,alien,him,bystander,animal') (Print 25 8))
					((Said 'look/eye') (Print 25 9))
					((Said 'look/lip') (Print 25 10))
					((Said 'look/cavity') (Print 25 11))
					(
					(Said 'converse[/man,clerk,alien,him,bystander,animal]') (Print 25 12))
					(
					(Said 'kiss/man,clerk,alien,him,bystander,animal') (Print 25 13))
					(
					(Said 'smell[/man,clerk,alien,him,bystander,animal]') (Print 25 14))
					((Said 'eat') (Print 25 15))
					(
						(or
							(Said 'look,read/menu')
							(Said 'order')
							(Said 'order,buy/dinner')
							(Said 'converse/clerk')
						)
						(curRoom newRoom: 280)
					)
				)
			)
			(keyDown
				(if (== (event message?) `#6)
					(event claimed: 1)
					(= saveDisabled 0)
					(= orderedBigBelcherCombo 0)
					(curRoom setScript: LeaveRoom)
				)
			)
			(mouseDown
				(if
					(and
						(<= 119 (event x?))
						(<= (event x?) 202)
						(<= 163 (event y?))
						(<= (event y?) 190)
					)
					(event claimed: TRUE)
					(= saveDisabled FALSE)
					(= orderedBigBelcherCombo FALSE)
					(curRoom setScript: LeaveRoom)
				)
			)
		)
	)
)

(instance Welcome of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveDisabled 1)
				(balloon init: setCel: 1 stopUpd:)
				(mouth setLoop: 0 setCycle: Forward)
				(= seconds 4)
			)
			(1
				(balloon setCel: 2)
				(= seconds 4)
			)
			(2
				(mouth setCel: 0 stopUpd:)
				(balloon dispose:)
				(curRoom setScript: 0)
				(= saveDisabled 0)
			)
		)
	)
)

(instance noMoney of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveDisabled 1)
				(if (== local0 1)
					(= local0 0)
					(eyes setMotion: MoveTo 155 74)
				)
				(balloon init: setCel: 4 stopUpd:)
				(mouth setLoop: 0 setCycle: Forward)
				(= seconds 7)
			)
			(1
				(= monolithBurgerBill FALSE)
				(= global247 0)
				(= saveDisabled FALSE)
				(= orderedBigBelcherCombo FALSE)
				(curRoom newRoom: 29)
			)
		)
	)
)

(instance LeaveRoom of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(balloon dispose:)
				(= cycles 1)
			)
			(1
				(Display 25 16 p_restore saveBits)
				(curRoom newRoom: 29)
			)
		)
	)
)

(instance PayUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveDisabled 1)
				(balloon init: setCel: 3 stopUpd:)
				(= saveBits
					(Display
						(Format @invStr 25 17 monolithBurgerBill)
						p_at 207 98
						p_width 30
						p_color vBLACK
						p_back vWHITE
						p_font 600
						p_save
					)
				)
				(mouth setLoop: 0 setCycle: Forward)
				(= seconds 7)
			)
			(1
				(mouth setCel: 0 stopUpd:)
				(balloon dispose:)
				(= cycles 2)
			)
			(2
				(Display 25 16 p_restore saveBits)
				(= saveDisabled FALSE)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance BoredMouth of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 15)))
			(1
				(if (!= (rm25 script?) 0)
					(self changeState: 0)
				else
					(mouth setLoop: 1 setCel: (Random 0 2))
				)
				(self changeState: 0)
			)
		)
	)
)

(instance BoredEyes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 12)))
			(1
				(cond 
					((!= (rm25 script?) 0) (self changeState: 0))
					((== local0 0)
						(= local0 1)
						(switch (Random 1 3)
							(1
								(eyes setMotion: MoveTo 155 61 self)
							)
							(2
								(eyes setMotion: MoveTo 172 71 self)
							)
							(3
								(eyes setMotion: MoveTo 139 71 self)
							)
						)
					)
					(else (= local0 0) (eyes setMotion: MoveTo 155 71 self))
				)
			)
			(2 (self changeState: 0))
		)
	)
)

(instance eyes of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 38 setLoop: 2 posn: 155 71 ignoreActors: 1)
	)
)

(instance mouth of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 38
			setLoop: 1
			setCel: 0
			posn: 155 137
			ignoreActors: 1
			cycleSpeed: 2
		)
	)
)

(instance balloon of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 38
			setLoop: 3
			setCel: 3
			posn: 206 119
			setPri: 15
		)
	)
)

(instance sine of PicView
	(properties
		y 89
		x 290
		view 38
		loop 3
		priority 15
		signal ignrAct
	)
)
