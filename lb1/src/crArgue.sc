;;; Sierra Script 1.0 - (do not remove this comment)
(script# 259)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	crArgue 0
)
(synonyms
	(butt cigarette)
)

(local
	rudyTalkCount
	local1
	clarTalkCount
	[local3 2]
	argueCount
)
(procedure (RudyPrint)
	(rHead setCel: -1 setCycle: Forward)
	(Rudy setCycle: EndLoop)
	(Print &rest
		#at 171 140
		#font 4
		#width 125
		#mode teJustCenter
		#dispose
	)
)

(procedure (ClarPrint)
	(Clarence setCycle: EndLoop)
	(CHead view: 404 loop: 3 setCycle: Forward)
	(Print &rest
		#at 80 140
		#font 4
		#width 125
		#mode teJustCenter
		#dispose
	)
)

(procedure (localproc_0084)
	(cast eachElementDo: #hide)
	(DrawPic 992 IRISIN TRUE 0)
)

(procedure (localproc_009b)
	(DrawPic 48 IRISOUT TRUE 0)
	(addToPics doit:)
	(cast eachElementDo: #show)
	(rHead hide:)
	(CHead hide:)
)

(instance crArgue of Region
	
	(method (init)
		(super init:)
		(if (not (& global118 $0004))
			(LoadMany FONT 4 41)
			(Load VIEW 642)
			(LoadMany SOUND 29 94 95 96)
			(Load SCRIPT 406)
		)
		(Load SCRIPT 985)
		(LoadMany 143 243 284 289)
		(LoadMany 142 7 9)
		(= global208 320)
		(= [global377 6] 284)
		(= [global377 8] 289)
		(Rudy init:)
		(rHead ignoreActors: TRUE init:)
		(CHead ignoreActors: TRUE init:)
		(Clarence init:)
		(Wisp
			setPri: 13
			ignoreActors: TRUE
			setCycle: Forward
			cycleSpeed: 1
			init:
		)
		(AshTray setPri: 13 ignoreActors: TRUE init: stopUpd:)
		(Smoke setPri: 12 ignoreActors: TRUE init: hide:)
		(glass setPri: 14 ignoreActors: TRUE init: stopUpd:)
		(self setScript: argue)
	)
	
	(method (doit)
		(super doit:)
		(if (> (ego y?) 158)
			(ego setPri: 14)
		else
			(ego setPri: -1)
		)
	)
	
	(method (dispose)
		(if (and (not (& global173 $0008)) (== [global368 2] 0))
			(= [global368 2] 1800)
		)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance argue of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0004))
						(|= global118 $0004)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?)
						(= state -1)
					)
				)
				(= cycles 1)
			)
			(1
				(User canControl: TRUE)
				(if (== (= argueCount (& global178 $7fff)) global178)
					(if (< global178 8)
						(++ global178)
					else
						(= global178 -32765)
					)
				else
					(switch argueCount
						(3 (= global178 -32764))
						(4 (= global178 -32762))
						(6 (= global178 -32760))
						(8 (= global178 -32765))
					)
				)
				(switch argueCount
					(1 (RudyPrint 259 0))
					(2 (ClarPrint 259 1))
					(3 (ClarPrint 259 2))
					(4 (RudyPrint 259 3))
					(5 (RudyPrint 259 4))
					(6 (ClarPrint 259 5))
					(7 (ClarPrint 259 6))
					(else  (RudyPrint 259 7))
				)
				(= seconds 7)
			)
			(2
				(cls)
				(rHead setCycle: 0)
				(CHead setCycle: 0)
				(if
					(or
						(== argueCount 2)
						(== argueCount 3)
						(== argueCount 6)
						(== argueCount 7)
					)
					(Clarence setCycle: BegLoop self)
				else
					(Rudy setCycle: BegLoop self)
				)
			)
			(3
				(switch argueCount
					(1
						(ClarPrint 259 8)
						(= seconds 5)
					)
					(2
						(ClarPrint 259 9)
						(= seconds 5)
					)
					(5
						(RudyPrint 259 10)
						(= seconds 5)
					)
					(6
						(RudyPrint 259 11)
						(= seconds 5)
					)
					(7
						(RudyPrint 259 12)
						(= seconds 5)
					)
					(else  (= cycles 1))
				)
			)
			(4
				(rHead setCycle: 0)
				(CHead setCycle: 0)
				(cls)
				(if (or (== argueCount 2) (== argueCount 1))
					(Clarence setCycle: BegLoop self)
				else
					(Rudy setCycle: BegLoop self)
				)
			)
			(5
				(HandsOn)
				(Rudy loop: 0 setScript: rudyActions)
				(Clarence setScript: clarActions)
				(client setScript: 0)
				(User canInput: 1)
			)
		)
	)
)

(instance rudyActions of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 6 12))
				(rHead show:)
			)
			(1
				(rHead setCel: -1 loop: 5 setCycle: EndLoop)
				(= seconds (Random 3 5))
			)
			(2
				(Rudy cel: 0 loop: 2 setCycle: EndLoop)
				(= seconds (Random 3 5))
			)
			(3
				(rHead setCycle: BegLoop)
				(= seconds (Random 3 5))
			)
			(4
				(Rudy loop: 3 setCycle: EndLoop)
				(= seconds (Random 3 5))
			)
			(5
				(rHead setCycle: EndLoop)
				(= seconds (Random 5 8))
			)
			(6
				(rHead setCycle: BegLoop)
				(= state 4)
				(= seconds (Random 8 16))
			)
		)
	)
)

(instance clarActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CHead hide:)
				(= cycles 1)
			)
			(1
				(CHead hide:)
				(Clarence
					view: 416
					cycleSpeed: 2
					loop: 3
					cel: 2
					x: 169
					y: 152
					z: 26
					setPri: 13
					illegalBits: 0
					ignoreActors: TRUE
					setCycle: BegLoop self
				)
			)
			(2
				(glass hide:)
				(Clarence loop: 2 cel: 3 setCycle: BegLoop self)
			)
			(3
				(Clarence loop: 1 cel: 0 setCycle: EndLoop)
				(= seconds 3)
			)
			(4
				(Clarence setCycle: BegLoop self)
			)
			(5
				(Clarence loop: 2 cel: 0 setCycle: EndLoop self)
			)
			(6
				(glass show:)
				(Clarence loop: 3 cel: 0 setCycle: EndLoop)
				(if (< (Random 1 100) 34)
					(= state 0)
				)
				(= seconds (Random 6 12))
			)
			(7
				(CHead
					show:
					view: 416
					posn: 172 161
					cel: 0
					loop: 5
					cycleSpeed: 2
					setCycle: EndLoop
				)
				(Clarence loop: 4 setCycle: EndLoop)
				(= seconds (Random 3 9))
			)
			(8
				(Clarence loop: 7 setCycle: Forward)
				(= seconds (Random 3 9))
			)
			(9
				(Clarence loop: 4 cel: 3 setCycle: BegLoop)
				(CHead setCycle: BegLoop)
				(= seconds (Random 3 9))
				(if (< (Random 1 100) 51)
					(= state 0)
				)
			)
			(10
				(CHead hide:)
				(Clarence view: 418 loop: 0 setCycle: EndLoop self)
			)
			(11
				(Wisp hide:)
				(Clarence loop: 1 setCycle: EndLoop self)
			)
			(12
				(Clarence loop: 2 setCycle: EndLoop)
				(= seconds 3)
			)
			(13
				(Smoke show: cel: 0 setCycle: EndLoop self)
				(Clarence setCycle: BegLoop)
			)
			(14
				(Clarence loop: 1 cel: 1 setCycle: BegLoop self)
				(Smoke hide:)
			)
			(15
				(Clarence loop: 0 cel: 2 setCycle: BegLoop self)
				(Wisp show:)
				(= state 0)
			)
		)
	)
)

(instance goSee of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Clarence setScript: 0)
				(= cycles 1)
			)
			(1
				(rHead hide:)
				(Rudy
					view: 380
					setCycle: Walk
					cycleSpeed: 0
					setPri: -1
					illegalBits: cWHITE
					setAvoider: ((Avoider new:) offScreenOK: TRUE)
					setMotion: MoveTo -20 96 self
				)
			)
			(2
				(= saveDisabled TRUE)
				(localproc_0084)
				(= seconds 5)
			)
			(3
				(localproc_009b)
				(Print 259 13 #dispose)
				(Rudy setMotion: MoveTo 201 142 self)
				(= saveDisabled FALSE)
			)
			(4
				(cls)
				(= theTalker talkRUDY)
				(Say 1 259 14)
				(HandsOn)
				(Rudy view: 382 loop: 2 cycleSpeed: 2 setAvoider: 0)
				(Rudy setScript: rudyActions)
				(Clarence setScript: clarActions)
			)
		)
	)
)

(instance rHead of Prop
	(properties
		y 142
		x 201
		z 41
		view 382
		loop 4
		cycleSpeed 1
	)
)

(instance Rudy of Actor
	(properties
		y 142
		x 201
		view 382
		loop 2
		cycleSpeed 2
	)
	
	(method (handleEvent event)
		(if (< (ego distanceTo: Rudy) (ego distanceTo: Clarence))
			(= global214 256)
		else
			(= global214 64)
		)
		(if (Said 'examine/person,fellow')
			(switch global214
				(256 (ParseName {rudy}))
				(64 (ParseName {clarence}))
			)
		)
		(= theTalker talkRUDY)
		(cond 
			((and (Btst fSawDeadGuest) (Said 'tell[/rudolph]/gertie<about'))
				(= theTalker talkRUDY)
				(if (& deadGuests $0001)
					(if (& global145 $0080)
						(Say 1 259 15)
					else
						(Say 1 259 16)
						(|= global145 $0080)
						(Rudy setScript: goSee)
					)
				else
					(event claimed: FALSE)
				)
			)
			((and (not (& global207 $0100)) (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(ParseName {rudy})
			)
			(
				(and
					(& global207 $0100)
					(or (MousedOn self event shiftDown) (Said 'examine/rudolph'))
				)
				(event claimed: TRUE)
				(Print 259 17)
			)
			((Said 'ask[/rudolph,fellow,person]/attorney<about')
				(Print 259 18)
			)
			((Said 'examine/men,people')
				(Print 259 17)
			)
			((Said 'converse/people,men')
				(Print 259 19)
			)
			(
				(or
					(Said '/rudolph>')
					(and
						(== global214 256)
						(Said 'converse/person,fellow>')
					)
				)
				(cond
					((Said 'converse')
						(switch rudyTalkCount
							(0 (Say 1 259 20))
							(1 (Say 1 259 21))
							(2 (Say 1 259 22))
							(else  (Say 1 259 23))
						)
						(++ rudyTalkCount)
					)
					((Said 'tell')
						(Print 259 18)
					)
					((Said 'hear')
						(Say 259 24)
					)
					((Said 'deliver,hold')
						(Print 259 25)
					)
				)
			)
		)
	)
)

(instance glass of Prop
	(properties
		y 138
		x 151
		view 416
	)
)

(instance CHead of Prop
	(properties
		y 145
		x 168
		z 40
		view 404
		cel 2
	)
)

(instance Clarence of Actor
	(properties
		y 145
		x 164
		view 402
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(= theTalker talkCLARENCE)
		(if (< (ego distanceTo: Rudy) (ego distanceTo: Clarence))
			(= global214 256)
		else
			(= global214 64)
		)
		(cond 
			( (and (not (& global207 $0040)) (MousedOn self event shiftDown))
				(event claimed: TRUE)
				(ParseName {clarence})
			)
			(
				(and
					(& global207 $0040)
					(or (MousedOn self event shiftDown) (Said 'examine/attorney'))
				)
				(event claimed: TRUE)
				(Print 259 17)
			)
			((Said 'ask[/attorney,fellow,person]/rudolph<about')
				(Print 259 26)
			)
			(
				(or
					(Said '/attorney>')
					(and (== global214 64) (Said 'converse/person,fellow>'))
				)
				(cond 
					((Said 'converse')
						(switch clarTalkCount
							(0 (Say 1 259 27))
							(1 (Say 1 259 28))
							(else  (Say 1 259 29))
						)
						(++ clarTalkCount)
					)
					((Said 'tell')
						(Print 259 26)
					)
					((Said 'hear')
						(Say 259 24)
					)
					((Said 'deliver,hold')
						(Print 259 30)
					)
				)
			)
		)
	)
)

(instance Smoke of Prop
	(properties
		y 120
		x 170
		view 418
		loop 3
	)
)

(instance AshTray of Prop
	(properties
		y 136
		x 151
		view 148
		loop 1
		cel 10
	)
	
	(method (handleEvent)
		(cond 
			((Said 'examine/drink,glass,alcohol')
				(Print 259 31)
			)
			((Said 'get/drink,glass,alcohol')
				(Print 259 32)
			)
			((Said 'get/butt')
				(Print 259 33)
			)
			((Said 'examine/ashtray,butt')
				(Print 259 34)
			)
			((Said 'drink/alcohol')
				(Print 259 35)
			)
		)
	)
)

(instance Wisp of Prop
	(properties
		y 135
		x 149
		view 418
		loop 4
	)
)
