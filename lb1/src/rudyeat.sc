;;; Sierra Script 1.0 - (do not remove this comment)
(script# 403)
(include game.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rudyeat 0
)
(synonyms
	(rudolph person fellow)
)

(local
	talkCount
	local1
)
(procedure (localproc_000c)
	(cast eachElementDo: #hide)
	(DrawPic 992 IRISIN TRUE 0)
)

(procedure (localproc_0023)
	(DrawPic 34 IRISOUT TRUE 0)
	(addToPics doit:)
	(cast eachElementDo: #show)
	(RHead hide:)
)

(instance rudyeat of Region
	
	(method (init)
		(super init:)
		(if (not (& global118 $0001))
			(LoadMany FONT 4 41)
			(LoadMany SOUND 29 94 95 96)
			(Load SCRIPT 406)
			(Load VIEW 642)
		)
		(LoadMany VIEW 380 908)
		(LoadMany 143 243 373)
		(= global208 256)
		(= [global377 8] 373)
		(RHead setPri: 9 init: hide:)
		(Plate ignoreActors: TRUE setPri: 9 init: stopUpd:)
		(Rudy ignoreActors: TRUE illegalBits: 0)
		(Rudy posn: 108 100 setPri: 9 init:)
		(self setScript: eatPie)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'converse>')
						(if (Said '/rudolph')
							(= theTalker talkRUDY)
							(switch talkCount
								(0
									(Say 1 403 0)
									(Say 1 403 1)
								)
								(1
									(Say 1 403 2)
									(= theTalker talkLAURA)
									(Say 0 403 3)
								)
								(2
									(Say 1 403 4)
									(= theTalker talkLAURA)
									(Say 0 403 5)
								)
								(3
									(Say 1 403 6)
									(= theTalker talkLAURA)
									(Say 0 403 7)
								)
								(4
									(Say 1 403 8)
									(= theTalker talkLAURA)
									(Say 0 403 9)
								)
								(5
									(Say 1 403 10)
								)
								(6
									(Say 1 403 11)
								)
								(7
									(Say 1 403 12)
								)
								(else
									(Print 403 13)
								)
							)
							(++ talkCount)
						)
					)
					((Said 'hear/rudolph')
						(Print 403 14)
					)
					((Said 'examine/desert,coffee')
						(Print 403 15)
					)
					((Said 'eat,get/desert,food')
						(Print 403 16)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance eatPie of Script
	
	(method (doit)
		(if
			(and
				(or (== (Rudy loop?) 3) (== (Rudy loop?) 6))
				(== (Rudy cel?) 0)
			)
			(RHead show:)
			(Rudy loop: 6 cel: 0)
		else
			(RHead hide:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
					)
					((not (& global118 $0001))
						(|= global118 $0001)
						(self setScript: (ScriptID 406 0))
						(= state -1)
					)
					((self script?) (= state -1))
				)
				(= cycles 1)
			)
			(1
				(Rudy
					view: 396
					setPri: 9
					cycleSpeed: 1
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(Rudy loop: 2 cel: 0 setCycle: Forward)
				(if (< (Random 1 100) 60) (= state -1))
				(= seconds 5)
			)
			(3
				(Rudy loop: 3 cel: 0 setCycle: EndLoop)
				(= seconds 5)
			)
			(4
				(Rudy loop: 3 setCycle: BegLoop)
				(= seconds (Random 3 6))
			)
			(5
				(RHead
					loop: (if (< (Random 1 100) 51) 4 else 5)
					setCycle: EndLoop
				)
				(= seconds (Random 3 6))
			)
			(6
				(RHead setCycle: BegLoop)
				(if (< (Random 1 100) 60)
					(= state -1)
				else
					(= state 2)
				)
				(= seconds (Random 3 8))
			)
		)
	)
)

(instance goSee of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cls)
				(HandsOff)
				(= local1 1)
				(Rudy
					view: 380
					setCycle: Walk
					cycleSpeed: 0
					setPri: -1
					illegalBits: -32768
					setAvoider: ((Avoider new:) offScreenOK: TRUE)
					setMotion: MoveTo 300 120 self
				)
				(RHead hide:)
			)
			(1
				(= saveDisabled TRUE)
				(localproc_000c)
				(= seconds 5)
			)
			(2
				(localproc_0023)
				(Print 403 17 #dispose)
				(Rudy setMotion: MoveTo 115 103 self)
				(= saveDisabled FALSE)
			)
			(3
				(cls)
				(= theTalker talkRUDY)
				(Say 1 403 18)
				(HandsOn)
				(Rudy setAvoider: 0)
				(= cycles 1)
			)
			(4
				(Rudy
					view: 396
					loop: 1
					cel: 0
					ignoreActors: TRUE
					illegalBits: 0
					posn: 108 100
					setPri: 9
					init:
				)
				(RHead show:)
				(rudyeat setScript: eatPie)
			)
		)
	)
)

(instance RHead of Prop
	(properties
		y 100
		x 104
		z 23
		view 396
		loop 4
	)
)

(instance Plate of Prop
	(properties
		y 99
		x 110
		view 396
		cel 1
	)
)

(instance Rudy of Actor
	(properties
		view 396
		loop 1
	)
	
	(method (handleEvent event)
		(cond 
			((and (Btst fSawDeadGuest) (Said 'tell[/rudolph]/actress<about'))
				(= theTalker 9)
				(if (& deadGuests deadGLORIA)
					(if (& global145 $0080)
						(Say 1 403 19)
					else
						(Say 1 403 20)
						(|= global145 $0080)
						(rudyeat setScript: goSee)
					)
				else
					(event claimed: FALSE)
				)
			)
			(
			(and (Btst fSawDeadGuest) (Said 'tell[/rudolph]/gertie<about'))
				(= theTalker talkRUDY)
				(if (& deadGuests deadGERTRUDE)
					(if (& global145 $0080)
						(Say 1 403 21)
					else
						(Say 1 403 20)
						(|= global145 $0080)
						(rudyeat setScript: goSee)
					)
				else
					(event claimed: FALSE)
				)
			)
			((Said 'get/cup,coffee')
				(Print 403 22)
			)
			((and (MousedOn self event shiftDown) (not (& global207 $0100)))
				(event claimed: TRUE)
				(ParseName {rudy})
			)
			(
				(and
					(& global207 $0100)
					(or (MousedOn self event shiftDown) (Said 'examine/rudolph'))
				)
				(event claimed: TRUE)
				(Print 403 15)
			)
		)
	)
)
