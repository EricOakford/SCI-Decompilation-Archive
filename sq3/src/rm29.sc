;;; Sierra Script 1.0 - (do not remove this comment)
(script# 29)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm29 0
)

(local
	local0
	local1
	local2
	local3
	warned
	local5
	standingUp
	local7
)
(instance rm29 of Room
	(properties
		picture 29
		east 28
	)
	
	(method (init &tmp [temp0 50])
		(User canInput: TRUE canControl: TRUE)
		(Load VIEW 56)
		(Load VIEW 57)
		(Load VIEW 59)
		(Load VIEW 85)
		(super init:)
		(door init: stopUpd:)
		(clerk init: stopUpd:)
		(ego init:)
		(bully init: stopUpd:)
		(if (> howFast 0)
			(al3 init: stopUpd: setScript: al3Script)
			(al1Head init: stopUpd: setScript: (al1Script new:))
			(al2Head init: stopUpd: setScript: (al1Script new:))
		else
			(al3 init: addToPic:)
			(al1Head init: addToPic:)
			(al2Head init: addToPic:)
		)
		(addToPics add: al1 al2 machine)
		(addToPics doit:)
		(= local0 2)
		(= warned FALSE)
		(= standingUp TRUE)
		(= local7 1)
		(switch prevRoomNum
			(25
				(HandsOn)
				(ego init: loop: 3 cel: 0 posn: 286 130)
				(if (and global247 global248)
					(HandsOff)
					(= global248 0)
					(curRoom setScript: makeBurger)
				)
				(= local1 1)
				(theMusic number: 16 loop: -1 play:)
			)
			(28
				(HandsOn)
				(ego init:)
				(if (< (ego y?) 110) (ego posn: 200 100))
			)
			(280
				(HandsOn)
				(ego init: posn: gGEgoX_3 gGEgoY_2)
				(theMusic number: 16 loop: -1 play:)
			)
			(290
				(TheMenuBar draw:)
				(StatusLine enable:)
				(HandsOn)
				(NormalEgo 0 0)
				(ego posn: 172 111)
				(theMusic number: 16 loop: -1 play:)
			)
			(else 
				(ego init: posn: 172 111)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(== (ego onControl: 0) 4)
				(== (ego onControl: 0) 5)
			)
			(curRoom newRoom: 28)
		)
		(if
			(and
				(or
					(== (ego onControl: 0) 16)
					(== (ego onControl: 0) 17)
				)
				(not script)
			)
			(curRoom setScript: SitDown)
		)
		(if (ego inRect: 282 125 319 135)
			(cond 
				(global247
					(if
					(and (not local1) (not global248) (not (ego has: iBagOfFastFood)))
						(curRoom newRoom: 25)
					)
				)
				((and (not local2) (not local1)) (Print 29 0) (= local2 1))
			)
		else
			(= local1 0)
			(= local2 0)
		)
		(if (and (== (ego onControl: 1) 8) (not script))
			(curRoom setScript: Bully)
		)
	)
	
	(method (handleEvent event &tmp [temp0 50])
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((or (Said '/pane') (Said '<out')) (Print 29 1))
							((or (Said '/deck,ceiling') (Said '<up,down')) (Print 29 2))
							((Said '/partition') (Print 29 3))
							((Said '/clerk') (Print 29 4))
							(
							(Said '/alien,being,man,bystander,folk,animal,customer') (Print 29 5))
							((Said '/craft') (Print 29 6))
							((Said '/counter') (Print 29 7))
							((Said '/door') (Print 29 8))
							((Said '/menace') (Print 29 9))
							((Said '/dinner,bag,drink')
								(cond 
									((ego has: iBagOfFastFood) (event claimed: FALSE))
									((== local7 0) (Print 29 10))
									(else (Print 29 11))
								)
							)
							(
								(or
									(Said '/astro,astro,grafitti,game,device,comp[<grafitti]')
									(Said '/astro<astro')
								)
								(if (ego inRect: 158 94 200 124)
									(curRoom newRoom: 290)
								else
									(Print 29 12)
								)
							)
							((Said '/table,chair,booth') (Print 29 13))
							((Said '/menu')
								(if (== standingUp 0)
									(Print 29 14)
								else
									(= gGEgoX_3 (ego x?))
									(= gGEgoY_2 (ego y?))
									(curRoom newRoom: 280)
								)
							)
							((Said '[<around,at,in][/area,cafe]') (Print 29 15))
						)
					)
					((Said 'open,close/door') (Print 29 16))
					(
						(or
							(Said 'play/astro,astro,grafitti,game,device[<grafitti]')
							(Said 'play/astro<astro')
						)
						(if (ego inRect: 158 94 200 124)
							(curRoom newRoom: 290)
						else
							(Print 29 12)
						)
					)
					(
					(Said 'beat,tilt/grafitti,game,device[<grafitti]')
						(if (ego inRect: 158 94 200 124)
							(Print 29 17)
						else
							(Print 29 12)
						)
					)
					(
						(or
							(Said 'order,buy,get,ask[/dinner,dinner,burger,drink]')
							(Said 'converse/clerk')
						)
						(cond 
							((not (ego inRect: 282 125 319 135)) (Print 29 18))
							((ego has: iBagOfFastFood) (Print 29 19))
							((not global248) (curRoom newRoom: 25))
							(else (Print 29 0))
						)
					)
					((Said 'read/menu')
						(if (== standingUp FALSE)
							(Print 29 14)
						else
							(= gGEgoX_3 (ego x?))
							(= gGEgoY_2 (ego y?))
							(curRoom newRoom: 280)
						)
					)
					((Said 'read/menace') (Print 29 9))
					(
						(Said
							'ask,converse/alien,being,man,bystander,folk,animal,customer'
						)
						(Print 29 20)
					)
					((Said 'converse') (Print 29 21))
					(
						(Said
							'kiss/alien,being,man,bystander,folk,animal,customer'
						)
						(Print 29 22)
					)
					(
						(Said
							'smell[/man,alien,being,him,bystander,animal,customer]'
						)
						(Print 29 23)
					)
					((Said 'down,sit[<down]')
						(cond 
							((== standingUp FALSE) (Print 29 24))
							((ego inRect: 173 123 232 156) (curRoom setScript: SitDown))
							(else (Print 29 25))
						)
					)
					((Said 'up,(get<up),stand[/up]')
						(if (== standingUp TRUE)
							(Print 29 26)
						else
							(curRoom setScript: StandUp)
						)
					)
					((Said 'eat[<dinner]')
						(cond 
							((!= standingUp FALSE) (Print 29 27))
							((ego has: iBagOfFastFood) (curRoom setScript: EgoEating))
							(else (Print 29 28))
						)
					)
					((Said 'get[<up]/bag')
						(cond 
							((ego has: iBagOfFastFood) (Print 29 29))
							((== local7 0) (Print 29 30))
							(else (Print 29 31))
						)
					)
					(
						(or
							(Said 'throw,get[<up,away]/garbage')
							(Said 'clean/table')
						)
						(cond 
							((ego has: iBagOfFastFood) (Print 29 32))
							((== local7 0) (Print 29 30))
							(else (Print 29 33))
						)
					)
				)
			)
			(direction
				(if (== standingUp 0)
					(switch (event message?)
						(dirN
							(curRoom setScript: StandUp)
							(event claimed: TRUE)
						)
						(dirE
							(curRoom setScript: StandUp)
							(event claimed: TRUE)
						)
						(dirS
							(event claimed: TRUE)
							(return)
						)
						(dirNW
							(event claimed: TRUE)
							(return)
						)
						(dirNE
							(curRoom setScript: StandUp)
							(event claimed: TRUE)
						)
						(dirSE
							(event claimed: TRUE)
							(return)
						)
						(dirSW
							(event claimed: TRUE)
							(return)
						)
						(dirW
							(event claimed: TRUE)
							(return)
						)
						(dirStop
							(event claimed: TRUE)
							(return)
						)
					)
				)
			)
			(mouseDown
				(if (== standingUp 0)
					(curRoom setScript: StandUp)
					(event claimed: TRUE)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (!= newRoomNumber 28) (theMusic stop:))
		(super newRoom: newRoomNumber)
	)
)

(instance makeBurger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(clerk setLoop: 3 setMotion: MoveTo 248 103 self)
			)
			(1 (= seconds 2))
			(2
				(clerk setLoop: 4 setMotion: MoveTo 297 105 self)
			)
			(3
				(clerk setLoop: 1)
				(= seconds 2)
			)
			(4
				(clerk setLoop: 0)
				(bag init:)
				(balloon init:)
				(= seconds 4)
			)
			(5
				(balloon dispose:)
				(bag dispose:)
				(Print 29 34)
				(ego get: iBagOfFastFood)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance SitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego illegalBits: 0 setMotion: MoveTo 186 141 self)
			)
			(1
				(ego view: 85 setLoop: 7 setPri: 15 setCel: 0)
				(if (ego has: iBagOfFastFood)
					(ego setCycle: EndLoop self)
				else
					(ego setCycle: CycleTo 4 1 self)
				)
			)
			(2
				(HandsOn)
				(= standingUp FALSE)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance StandUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (== local7 0) (bag2 init:) (ego put: iBagOfFastFood))
				(ego setLoop: 7 setCel: 4 setCycle: BegLoop self)
			)
			(1
				(ego posn: 191 139)
				(RedrawCast)
				(NormalEgo 2 0)
				(= standingUp TRUE)
				(HandsOn)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance EgoEating of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= inCartoon 1)
				(= standingUp 2)
				(= local5 7)
				(ego setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(cond 
					((!= (-- local5) 0)
						(switch (Random 1 2)
							(1 (self changeState: 10))
							(2 (self changeState: 20))
						)
					)
					(mealHasDecoderRing (self changeState: 30))
					(else (self changeState: 40))
				)
			)
			(10
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(11 (ego setCycle: BegLoop self))
			(12
				(ego setLoop: 2 setCycle: Forward)
				(= seconds 2)
			)
			(13
				(ego setLoop: 3 setCycle: EndLoop self)
			)
			(14
				(ego setLoop: 2 setCel: 0)
				(= cycles 10)
			)
			(15 (self changeState: 1))
			(20
				(ego setLoop: 4 setCel: 0 setCycle: EndLoop self)
			)
			(21
				(ego setLoop: 5 setCycle: Forward)
				(= seconds 3)
			)
			(22
				(ego setLoop: 4 setCel: 2 setCycle: BegLoop self)
			)
			(23
				(ego setLoop: 2 setCel: 0)
				(= cycles 10)
			)
			(24 (self changeState: 1))
			(30
				(ego setLoop: 1 setCel: 0 setCycle: EndLoop self)
			)
			(31 (ego setCycle: BegLoop self))
			(32
				(ego setLoop: 2 setCycle: Forward)
				(= seconds 4)
			)
			(33
				(Print 29 35)
				(ego get: iDecoderRing)
				(theGame changeScore: 10)
				(= seconds 3)
			)
			(34 (self changeState: 40))
			(40
				(Print 29 36)
				(ego setCycle: 0)
				(User canInput: 1)
				(= inCartoon 0)
				(= mealHasDecoderRing FALSE)
				(= monolithBurgerBill 0)
				(ego put: iBagOfFastFood)
				(= local7 0)
				(= standingUp 0)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance Bully of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not warned)
					(= warned TRUE)
					(HandsOff)
					(bBalloon init:)
					(= seconds 3)
				else
					(HandsOff)
					(self changeState: 20)
				)
			)
			(1
				(bBalloon dispose:)
				(ShakeScreen 5)
				(pow init: setCycle: EndLoop)
				(door setCycle: EndLoop self)
			)
			(2
				(ego
					view: 57
					setLoop: 5
					setCel: 0
					posn: 146 89
					xStep: 5
					yStep: 3
					cycleSpeed: 1
					setCycle: EndLoop
					setMotion: MoveTo 169 113 self
					ignoreControl: 1
				)
			)
			(3
				(stars init: setCycle: Forward)
				(pow
					setLoop: 4
					setCel: 0
					posn: (ego x?) (+ (ego y?) 5)
					setPri: 15
					setCycle: Forward
				)
				(= seconds 2)
			)
			(4 (pow dispose:) (= cycles 2))
			(5
				(bully setCycle: Walk setMotion: MoveTo 146 89 self)
			)
			(6
				(bully setMotion: MoveTo 158 97 self)
			)
			(7
				(door setCycle: BegLoop)
				(bully setMotion: MoveTo 209 97 self)
			)
			(8
				(bully stopUpd:)
				(stars dispose:)
				(ego setLoop: 6 setCycle: EndLoop self)
			)
			(9
				(NormalEgo 1 0)
				(HandsOn)
				(curRoom setScript: 0)
			)
			(20
				(door setCycle: EndLoop)
				(bully setCycle: Walk setMotion: MoveTo 158 97 self)
			)
			(21
				(bully setMotion: MoveTo 146 89 self)
			)
			(22
				(bully setMotion: MoveTo 122 89 self)
			)
			(23 (door setCycle: BegLoop self))
			(24
				(bBalloon init: setCel: 1)
				(= seconds 3)
			)
			(25
				(bBalloon dispose:)
				(ShakeScreen 5)
				(pow
					init:
					setLoop: 3
					setCel: 0
					posn: 145 81
					setPri: 15
					setCycle: EndLoop
				)
				(= seconds 2)
			)
			(26 (EgoDead 0 0 10 12))
		)
	)
)

(instance al1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 10)))
			(1
				(if
				(or local3 (!= (ego mover?) 0) (!= (rm29 script?) 0))
					(self changeState: 5)
				else
					(= cycles 2)
				)
			)
			(2
				(= local3 1)
				(client setCycle: Forward)
				(= seconds 3)
			)
			(3
				(client stopUpd:)
				(= local3 0)
				(= seconds (Random 10 20))
			)
			(4 (self changeState: 1))
			(5 (= cycles 2))
			(6 (self changeState: 1))
		)
	)
)

(instance al3Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 14)))
			(1
				(if
				(or local3 (!= (ego mover?) 0) (!= (rm29 script?) 0))
					(self changeState: 6)
				else
					(= cycles 2)
				)
			)
			(2
				(= local3 1)
				(client setCycle: EndLoop)
				(= seconds 2)
			)
			(3 (client setCycle: BegLoop self))
			(4
				(client stopUpd:)
				(= local3 0)
				(= seconds (Random 10 20))
			)
			(5 (self changeState: 1))
			(6 (= cycles 2))
			(7 (self changeState: 1))
		)
	)
)

(instance clerk of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self view: 59 loop: 0 posn: 297 105 ignoreActors: 1)
	)
)

(instance bag of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 59
			setLoop: 2
			setCel: 0
			posn: 295 108
			setPri: 9
			ignoreActors: TRUE
		)
	)
)

(instance bag2 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 85
			setLoop: 6
			setCel: 0
			posn: 177 153
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance balloon of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 59
			setLoop: 5
			setCel: 0
			posn: 297 85
			ignoreActors: TRUE
		)
	)
)

(instance bully of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 57
			loop: 0
			posn: 122 89
			ignoreActors: TRUE
			setStep: 3 2
		)
	)
)

(instance bBalloon of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 57
			setLoop: 2
			setCel: 0
			posn: 177 88
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance pow of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 57
			setLoop: 3
			setCel: 0
			posn: 145 81
			setPri: 15
		)
	)
)

(instance stars of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 57
			setLoop: 7
			setCel: 0
			posn: (+ (ego x?) 3) (- (ego y?) 9)
			setPri: (+ (ego priority?) 1)
		)
	)
)

(instance al1 of PicView
	(properties
		y 164
		x 237
		view 56
		loop 1
		cel 1
		priority 15
		signal ignrAct
	)
)

(instance al2 of PicView
	(properties
		y 134
		x 140
		view 56
		loop 1
		cel 2
		priority 15
		signal ignrAct
	)
)

(instance machine of PicView
	(properties
		y 111
		x 190
		view 56
		loop 1
	)
)

(instance al3 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 56
			setLoop: 4
			setCel: 0
			posn: 289 170
			setPri: 15
		)
	)
)

(instance al1Head of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 56
			setLoop: 2
			setCel: 0
			posn: 141 119
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance al2Head of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 56
			setLoop: 3
			setCel: 0
			posn: 226 149
			setPri: 15
			ignoreActors: TRUE
		)
	)
)

(instance door of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 56
			setLoop: 0
			setCel: 0
			posn: 156 95
			setPri: 6
			ignoreActors: TRUE
		)
	)
	
	(method (doit)
		(super doit:)
		(if (not (rm29 script?))
			(cond 
				(
					(or
						(== (ego onControl: 0) 2)
						(== (ego onControl: 0) 3)
					)
					(if (> local0 1)
						(= local0 1)
						(self setCycle: EndLoop self)
					)
				)
				((< local0 2) (= local0 3) (self setCycle: BegLoop self))
			)
		)
	)
	
	(method (cue)
		(door stopUpd:)
		(= local0 (if (== local0 1) 0 else 2))
	)
)
