;;; Sierra Script 1.0 - (do not remove this comment)
(script# 28)
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
	rm028 0
)

(local
	local0
	local1
	local2
	local3
	standingUp
	local5
	local6
	local7
)
(instance rm028 of Room
	(properties
		picture 28
		west 29
	)
	
	(method (init &tmp [temp0 50])
		(Load VIEW 43)
		(Load VIEW 44)
		(Load VIEW 53)
		(Load VIEW 64)
		(Load VIEW 137)
		(Load VIEW 213)
		(Load SOUND 16)
		(super init:)
		(addToPics add: al2 al3 al5 al4 slop sa3)
		(addToPics doit:)
		(ship init:)
		(collar init: stopUpd:)
		(door init: stopUpd:)
		(if (> howFast 0)
			(al1 init: setScript: AlienScript)
			(arm init: stopUpd:)
			(tail init: stopUpd:)
			(sa2 init: stopUpd:)
			(sa3Mouth init: stopUpd:)
			(sa1 init: stopUpd: setScript: sa1Script)
		else
			(al1 init: addToPic:)
			(arm init: addToPic:)
			(tail init: addToPic:)
			(sa1 init: addToPic:)
			(sa2 init: addToPic:)
			(sa3Mouth init: addToPic:)
		)
		(= local1 2)
		(= standingUp TRUE)
		(= local5 1)
		(= monolithBurgerBill 0)
		(= global247 1)
		(theMusic number: 16)
		(if (and (!= prevRoomNum 29) (!= prevRoomNum 27))
			(theMusic play:)
		)
		(switch prevRoomNum
			(17
				(collar setCel: 7)
				(ship x: 335 y: 174)
				(chairMan setCel: 0)
				(HandsOff)
				(= global206 1)
				(= local0 4)
				(self setScript: dockScript)
			)
			(19
				(ship x: 335 y: 174)
				(collar setCel: 7)
				(chairMan setCel: 0)
				(HandsOff)
				(= global206 1)
				(= local0 4)
				(self setScript: dockScript)
			)
			(27
				(collar setCel: 7)
				(ship x: 335 y: 174)
				(chairMan setCel: 0)
				(HandsOff)
				(= global206 1)
				(= local0 4)
				(self setScript: dockScript)
			)
			(29
				(HandsOn)
				(collar setCel: 0)
				(ship x: 286 y: 115)
				(chairMan setCel: 7 init:)
				(ship stopUpd:)
				(= global206 0)
				(= local0 1)
				(ego init:)
				(if (< (ego y?) 110) (ego posn: 120 100))
			)
			(280
				(collar setCel: 0)
				(ship x: 286 y: 115 stopUpd:)
				(chairMan setCel: 7 init:)
				(HandsOn)
				(ego init: posn: gGEgoX_3 gGEgoY_2)
				(ship stopUpd:)
				(= global206 0)
				(= local0 1)
			)
		)
		(TheMenuBar draw:)
		(StatusLine enable:)
	)
	
	(method (doit)
		(super doit:)
		(if
			(or
				(== (ego onControl: 0) 4)
				(== (ego onControl: 0) 5)
			)
			(curRoom newRoom: 29)
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
		(if (and (== script 0) (ego inRect: 0 125 68 138))
			(rm028 setScript: CrowdScript)
		)
	)
	
	(method (handleEvent event &tmp [temp0 50])
		(if (event claimed?) (return))
		(switch (event type?)
			(saidEvent
				(cond 
					((Said 'look>')
						(cond 
							((or (Said '/pane') (Said '<out')) (Print 28 0))
							((or (Said '/deck,ceiling') (Said '<up,down')) (Print 28 1))
							((Said '/partition') (Print 28 2))
							((Said '/clerk') (Print 28 3))
							((Said '/alien,being,man,bystander,folk,animal') (Print 28 4))
							((Said '/craft') (Print 28 5))
							((Said '/door') (Print 28 6))
							((Said '/dinner,bag,drink')
								(cond 
									((ego has: iBagOfFastFood) (event claimed: FALSE))
									((== local5 0) (Print 28 7))
									(else (Print 28 8))
								)
							)
							((Said '/table,chair,booth') (Print 28 9))
							((Said '/counter') (Print 28 10))
							((Said '/menu')
								(if (== standingUp FALSE)
									(Print 28 11)
								else
									(= gGEgoX_3 (ego x?))
									(= gGEgoY_2 (ego y?))
									(curRoom newRoom: 280)
								)
							)
							((Said '[<around,at,in][/area,cafe]') (Print 28 12))
						)
					)
					((Said 'down,sit[<down]')
						(cond 
							((== standingUp FALSE) (Print 28 13))
							((ego inRect: 136 115 178 140) (curRoom setScript: SitDown))
							((ego inRect: 0 145 37 171) (Print 28 14))
							(else (Print 28 15))
						)
					)
					((Said 'up,(get<up),stand[/up]')
						(if (== standingUp TRUE)
							(Print 28 16)
						else
							(curRoom setScript: StandUp)
						)
					)
					((Said 'eat[<dinner]')
						(cond 
							((!= standingUp FALSE) (Print 28 17))
							((ego has: 17) (curRoom setScript: EgoEating))
							(else (Print 28 18))
						)
					)
					((Said 'get[<up]/bag')
						(cond 
							((ego has: iBagOfFastFood) (Print 28 19))
							((== local5 0) (Print 28 20))
							(else (Print 28 21))
						)
					)
					(
						(or
							(Said 'throw,get[<up,away]/garbage')
							(Said 'clean/table')
						)
						(cond 
							((ego has: iBagOfFastFood) (Print 28 22))
							((or (== local5 0) (ego inRect: 0 145 37 171)) (Print 28 20))
							(else (Print 28 23))
						)
					)
					((Said 'open,close/door') (Print 28 24))
					(
						(or
							(Said 'order,buy,get,ask[/dinner,dinner,burger]')
							(Said 'converse/clerk')
						)
						(Print 28 25)
					)
					(
						(Said
							'ask,converse/alien,being,man,bystander,folk,animal,customer'
						)
						(Print 28 26)
					)
					((Said 'converse') (Print 28 27))
					(
						(Said
							'kiss/alien,being,man,bystander,folk,animal,customer'
						)
						(Print 28 28)
					)
					(
						(Said
							'smell[/man,being,alien,him,bystander,animal,customer]'
						)
						(Print 28 29)
					)
					(
						(Said
							'beat[/man,being,alien,him,bystander,animal,customer]'
						)
						(Print 28 30)
					)
					((Said 'read/menu')
						(if (== standingUp FALSE)
							(Print 28 11)
						else
							(= gGEgoX_3 (ego x?))
							(= gGEgoY_2 (ego y?))
							(curRoom newRoom: 280)
						)
					)
					(
						(or
							(Said 'open,enter,board,climb,(get<in)[/door,door,craft]')
							(Said 'disembark')
						)
						(cond 
							((not (ego inRect: 179 72 251 95)) (Print 28 31))
							((ego has: iBagOfFastFood) (Print 28 32))
							(orderedBigBelcherCombo (self setScript: VomitScript))
							(else (self setScript: OutScript))
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
							(event claimed: TRUE)
							(return)
						)
						(dirS
							(event claimed: TRUE)
							(return)
						)
						(dirNW
							(curRoom setScript: StandUp)
							(event claimed: TRUE)
						)
						(dirNE
							(event claimed: TRUE)
							(return)
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
							(curRoom setScript: StandUp)
							(event claimed: TRUE)
						)
						(dirStop
							(event claimed: TRUE)
							(return)
						)
					)
				)
			)
			(mouseDown
				(if (and (== standingUp 0) (not (event claimed?)))
					(curRoom setScript: StandUp)
					(event claimed: TRUE)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(if (!= newRoomNumber 29) (theMusic fade:))
		(super newRoom: newRoomNumber)
	)
)

(instance SitDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 213
					illegalBits: 0
					posn: 168 127
					setLoop: 7
					setPri: 14
					setCel: 0
				)
				(if (ego has: iBagOfFastFood)
					(ego setCycle: EndLoop self)
				else
					(ego setCycle: CycleTo 4 1 self)
				)
			)
			(1
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
				(if (== local5 0) (bag2 init: stopUpd:) (ego put: iBagOfFastFood))
				(ego setLoop: 7 setCel: 4 setCycle: BegLoop self)
			)
			(1
				(RedrawCast)
				(NormalEgo 2 0)
				(ego posn: 166 125)
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
				(= local6 7)
				(ego setLoop: 0 setCel: 0 setCycle: EndLoop self)
			)
			(1
				(cond 
					((!= (-- local6) 0)
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
				(Print 28 33)
				(ego get: 7)
				(theGame changeScore: 10)
				(= seconds 3)
			)
			(34 (self changeState: 40))
			(40
				(Print 28 34)
				(ego setCycle: 0)
				(User canInput: 1)
				(= inCartoon 0)
				(= mealHasDecoderRing FALSE)
				(= monolithBurgerBill 0)
				(ego put: iBagOfFastFood)
				(= local5 0)
				(= standingUp FALSE)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance dockScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ship setMotion: MoveTo 286 125 self)
			)
			(1
				(ship setMotion: MoveTo 286 115 self)
			)
			(2
				(collar setCycle: BegLoop self startUpd:)
				(ship stopUpd:)
			)
			(3
				(collar stopUpd:)
				(= global206 0)
				(= seconds 2)
			)
			(4
				(Print 28 35 #at -1 130 #width 280)
				(HandsOn)
				(= cycles 2)
			)
			(5
				(chairMan init: setCycle: EndLoop self)
			)
			(6
				(chairMan stopUpd:)
				(= seconds 2)
			)
			(7
				(Print 28 36)
				(ego
					view: 0
					setLoop: -1
					loop: 1
					posn: 173 93
					setStep: 3 2
					setCycle: Walk
					illegalBits: cWHITE
					setPri: -1
					init:
				)
				(if (cast contains: ego) (ego show:) else (ego init:))
				(HandsOn)
				(= local0 1)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance OutScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 202 82 self)
			)
			(1
				(= inCartoon 1)
				(chairMan setCycle: BegLoop self)
				(ego hide:)
			)
			(2
				(= local0 4)
				(chairMan dispose:)
				(RedrawCast)
				(Print 28 37 #at -1 130 #width 280)
				(ship setMotion: MoveTo 286 125 self)
			)
			(3
				(ship setMotion: MoveTo 335 174 self)
			)
			(4
				(= global206 3)
				(= inCartoon 0)
				(curRoom newRoom: 14)
			)
		)
	)
)

(instance VomitScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 137
					setLoop: 0
					illegalBits: 0
					setMotion: MoveTo 168 93 self
				)
			)
			(1
				(ego setMotion: MoveTo 134 105 self)
			)
			(2
				(ego setCel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(3 (= cycles 10))
			(4 (ego setCycle: EndLoop self))
			(5 (= cycles 10))
			(6 (ego setCycle: EndLoop self))
			(7
				(ego
					view: 0
					loop: 1
					setLoop: -1
					illegalBits: -32768
					setCycle: Walk
				)
				(= seconds 2)
			)
			(8
				(Print 28 38)
				(= orderedBigBelcherCombo FALSE)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance CrowdScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(pow
					init:
					setPri: 14
					posn: (ego x?) (- (ego y?) 40)
					setCycle: EndLoop self
				)
				(ego
					view: 64
					setLoop: 2
					setCel: 0
					setCycle: EndLoop self
					xStep: 6
					setMotion: MoveTo (+ (ego x?) 30) (ego y?)
				)
			)
			(1
				(pow dispose:)
				(balloon
					init:
					setPri: 14
					posn: (ego x?) (- (ego y?) 40)
				)
				(= seconds 2)
			)
			(2
				(ego setLoop: 3 setCel: 0 setCycle: EndLoop self)
			)
			(3
				(balloon dispose:)
				(ego
					view: 0
					setLoop: -1
					loop: 1
					setCycle: Walk
					cycleSpeed: 0
					xStep: 3
					setDirection: 0
				)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance AlienScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 3 10)))
			(1
				(if
				(or local2 (!= (ego mover?) 0) (!= (rm028 script?) 0))
					(self changeState: 4)
				else
					(= cycles 2)
				)
			)
			(2
				(= local2 1)
				(switch (= local7 (Random 0 4))
					(0 (al1 setCycle: Forward))
					(1 (arm setCycle: Forward))
					(2 (tail setCycle: Forward))
					(3 (sa2 setCycle: Forward))
					(4 (sa3Mouth setCycle: Forward))
				)
				(= seconds 3)
			)
			(3
				(switch local7
					(0 (al1 setCycle: 0 stopUpd:))
					(1 (arm setCycle: 0 stopUpd:))
					(2 (tail setCycle: 0 stopUpd:))
					(3 (sa2 setCycle: 0 stopUpd:))
					(4
						(sa3Mouth setCycle: 0 stopUpd:)
					)
				)
				(= local2 0)
				(= cycles 2)
			)
			(4 (= cycles 2))
			(5 (self changeState: 1))
		)
	)
)

(instance sa1Script of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 5)))
			(1
				(if
				(or local2 (!= (ego mover?) 0) (!= (rm028 script?) 0))
					(self changeState: 13)
				else
					(= cycles 2)
				)
			)
			(2
				(= local2 1)
				(switch (Random 1 3)
					(1 (self changeState: 3))
					(2 (self changeState: 6))
					(3 (self changeState: 9))
				)
			)
			(3
				(sa1 setLoop: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(4 (sa1 setCycle: BegLoop self))
			(5 (self changeState: 12))
			(6
				(sa1 setLoop: 1 setCycle: EndLoop self)
			)
			(7
				(sa1 setLoop: 2 setCycle: Forward)
				(= seconds 3)
			)
			(8
				(sa1 setLoop: 0)
				(self changeState: 12)
			)
			(9
				(sa1 setLoop: 3 setCycle: EndLoop self)
			)
			(10
				(sa1 setLoop: 4 setCycle: Forward)
				(= seconds 3)
			)
			(11
				(sa1 setLoop: 0)
				(self changeState: 12)
			)
			(12
				(sa1 setLoop: 0 setCycle: 0 stopUpd:)
				(= local2 0)
				(= seconds (Random 10 20))
			)
			(13 (= cycles 2))
			(14 (self changeState: 1))
		)
	)
)

(instance bag2 of View
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 213
			setLoop: 6
			setCel: 0
			posn: 176 140
			setPri: 15
			ignoreActors: 1
		)
	)
)

(instance collar of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 44
			setLoop: 0
			posn: 286 90
			setPri: 6
			ignoreActors: 1
		)
	)
)

(instance ship of Actor
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 44
			setLoop: 1
			setPri: 5
			setStep: 1 1
			illegalBits: 0
			ignoreActors: 1
		)
	)
)

(instance al1 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			x: 30
			y: 128
			view: 43
			setLoop: 1
			setCel: 0
			setPri: 8
		)
	)
)

(instance al2 of PicView
	(properties
		y 137
		x 42
		view 43
		loop 2
		priority 10
	)
)

(instance al3 of PicView
	(properties
		y 137
		x 11
		view 43
		loop 3
		priority 10
	)
)

(instance al4 of PicView
	(properties
		y 137
		x 69
		view 43
		loop 4
	)
)

(instance al5 of PicView
	(properties
		y 121
		x 95
		view 43
		loop 4
		cel 1
	)
)

(instance slop of PicView
	(properties
		y 171
		x 22
		view 43
		loop 7
		priority 15
		signal ignrAct
	)
)

(instance sa3 of PicView
	(properties
		y 145
		x 145
		view 53
		loop 6
		priority 14
	)
)

(instance arm of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 43
			setLoop: 5
			setCel: 0
			x: 70
			y: 128
			setPri: 10
		)
	)
)

(instance tail of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 43
			setLoop: 6
			setCel: 0
			x: 123
			y: 125
			setPri: 8
		)
	)
)

(instance sa1 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 53
			setLoop: 0
			setCel: 0
			x: 88
			y: 160
			setPri: 14
		)
	)
)

(instance sa2 of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 53
			setLoop: 5
			setCel: 0
			x: 118
			y: 151
			setPri: 14
		)
	)
)

(instance sa3Mouth of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 53
			setLoop: 7
			setCel: 0
			x: 141
			y: 142
			setPri: 15
		)
	)
)

(instance chairMan of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 44
			setLoop: 2
			posn: 263 109
			setPri: 6
			ignoreActors: 1
		)
	)
)

(instance pow of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self view: 64 setLoop: 0 setCel: 0 ignoreActors: 1)
	)
)

(instance balloon of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self view: 64 setLoop: 1 setCel: 0 ignoreActors: 1)
	)
)

(instance door of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: 43
			setLoop: 0
			setCel: 0
			posn: 164 95
			setPri: 6
			ignoreActors: 1
		)
	)
	
	(method (doit)
		(super doit:)
		(cond 
			(
				(or
					(== (ego onControl: 0) 2)
					(== (ego onControl: 0) 3)
				)
				(if (> local1 1)
					(= local1 1)
					(self setCycle: EndLoop self)
				)
			)
			((< local1 2) (= local1 3) (self setCycle: BegLoop self))
		)
	)
	
	(method (cue)
		(door stopUpd:)
		(= local1 (if (== local1 1) 0 else 2))
	)
)
