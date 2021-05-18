;;; Sierra Script 1.0 - (do not remove this comment)
(script# 217)
(include game.sh)
(use Main)
(use atsgl)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	GertieClarArgue 0
)
(synonyms
	(butt cigarette)
	(gertie girl)
	(attorney fellow)
)

(local
	gertieTalkCount
	clarenceTalkCount
	local2
	local3
	local4
	local5
	argueCount
)
(procedure (GertiePrint)
	(Gertie loop: 2 cycleSpeed: 1 setCycle: Forward)
	(Clarence setCycle: 0)
	(Print &rest
		#at 10 140
		#font 4
		#width 125
		#mode teJustCenter
		#draw
		#dispose
	)
)

(procedure (ClarPrint)
	(Gertie loop: 1 cel: 1 stopUpd:)
	(Clarence loop: 3 cel: 0 cycleSpeed: 1 setCycle: Forward)
	(Print &rest
		#at 101 140
		#font 4
		#width 125
		#mode teJustCenter
		#draw
		#dispose
	)
)

(instance GertieClarArgue of Region
	
	(method (init)
		(super init:)
		(if (not (& global173 $0002))
			(LoadMany FONT 4 41)
			(Load VIEW 642)
			(LoadMany SOUND 29 94 95 96)
			(Load SCRIPT 406)
		)
		(LoadMany 143 243 218)
		(LoadMany 142 7 1)
		(= global208 64)
		(= [global377 6] 218)
		(= [global377 0] 216)
		(ClarAss init: stopUpd:)
		(Clarence setPri: 6 init:)
		(Smoke setPri: 6 init: hide:)
		(ego observeBlocks: gBlock)
		(if (not (& global173 $0002))
			(LoadMany 143 216)
			(Gertie init:)
			(self setScript: argue)
			(|= global208 $0001)
		)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(if (and (not (& global173 $0002)) (== [global368 1] 0))
			(= [global368 1] 1800)
		)
		(DisposeScript 204)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'examine>')
						(cond 
							((Said '/butt')
								(Bset fExaminedCigar)
								(Print 217 0)
							)
							((Said '/drink,glass')
								(Print 217 1)
							)
						)
					)
					((Said 'hear/attorney,gertie')
						(Print 217 2)
					)
					((Said 'get/butt')
						(Print 217 3)
					)
					((Said 'get/drink,glass')
						(Print 217 4)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance argue of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216)
						(= state -1)
					)
					((not (& global118 $0002))
						(|= global118 $0002)
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
				(User canInput: FALSE)
				(if (== (= argueCount (& global172 $7fff)) global172)
					(if (< global172 10)
						(++ global172)
					else
						(= global172 -32767)
					)
				else
					(switch argueCount
						(1 (= global172 -32766))
						(2 (= global172 -32763))
						(5 (= global172 -32762))
						(6 (= global172 -32759))
						(9 (= global172 -32758))
						(10 (= global172 -32767))
					)
				)
				(switch argueCount
					(0 (ClarPrint 217 5))
					(1 (GertiePrint 217 6))
					(2 (ClarPrint 217 7))
					(3 (GertiePrint 217 8))
					(4 (ClarPrint 217 9))
					(5 (GertiePrint 217 10))
					(6 (ClarPrint 217 11))
					(7 (GertiePrint 217 12))
					(8 (ClarPrint 217 13))
					(9 (GertiePrint 217 14))
					(10 (ClarPrint 217 15))
				)
				(= seconds 5)
			)
			(2
				(cls)
				(switch argueCount
					(0 (ClarPrint 217 16))
					(3 (GertiePrint 217 17))
					(7 (ClarPrint 217 18))
					(8 (GertiePrint 217 19))
					(else 
						(Clarence setCycle: 0)
						(Gertie loop: 1 cel: 1 stopUpd:)
					)
				)
				(= seconds 3)
			)
			(3
				(cls)
				(Gertie loop: 1 cel: 1 stopUpd: setScript: gertActions)
				(Clarence stopUpd: setScript: clarActions)
				(User canInput: TRUE)
				(client setScript: 0)
			)
		)
	)
)

(instance gertActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Gertie loop: 1 cel: 1 forceUpd:)
				(= seconds (Random 3 8))
			)
			(1
				(Gertie loop: 1 cel: 0 forceUpd:)
				(= state (Random -1 1))
				(= seconds (Random 3 8))
			)
			(2
				(Gertie loop: 0 cel: 0 setCycle: EndLoop)
				(= seconds (Random 3 5))
			)
			(3
				(Gertie loop: 0 cel: 0 setCycle: EndLoop)
				(= seconds (Random 3 8))
			)
			(4
				(Gertie loop: 3 setCycle: Forward)
				(= seconds (Random 3 8))
			)
			(5
				(Gertie setCycle: 0 stopUpd:)
				(= state -1)
				(= cycles 1)
			)
		)
	)
)

(instance clarActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= state 5)
				(= cycles 1)
			)
			(1
				(Clarence setLoop: 1 cel: 0 setCycle: EndLoop self)
				(= local3 (Random 1 3))
				(= local4 0)
			)
			(2
				(= seconds 2)
			)
			(3
				(Clarence setLoop: 4 cel: 1)
				(= cycles 1)
			)
			(4
				(Smoke cel: 0 setCycle: EndLoop self show:)
			)
			(5
				(if (< local4 local3)
					(++ local4)
					(Clarence cel: 0)
					(= cycles (= state 1))
				else
					(Smoke cel: 0 hide:)
					(Clarence setLoop: 5)
					(Clarence
						cel: (- (NumCels Clarence) 3)
						cycleSpeed: 2
						setCycle: BegLoop
					)
					(= seconds (Random 3 6))
				)
			)
			(6
				(Clarence setLoop: 2)
				(Clarence cel: (- (NumCels Clarence) 1))
				(= cycles 1)
			)
			(7
				(Clarence setCycle: BegLoop)
				(switch (Random 1 4)
					(1 (= state 0))
					(3 (= state 8))
				)
				(= seconds (Random 3 6))
			)
			(8
				(Clarence setCycle: EndLoop)
				(= seconds (Random 3 (= state 6)))
			)
			(9
				(Clarence setLoop: 9 cel: 0 setCycle: EndLoop)
				(= seconds (Random 1 2))
			)
			(10
				(Clarence setCycle: BegLoop)
				(= seconds (Random 3 6))
				(if (< seconds 5)
					(= state 0)
				else
					(= state 5)
				)
			)
		)
	)
)

(instance Gertie of Prop
	(properties
		y 103
		x 125
		view 342
	)
	
	(method (handleEvent event)
		(if (< (ego distanceTo: Gertie) (ego distanceTo: Clarence))
			(= global214 1)
		else
			(= global214 64)
		)
		(= theTalker talkGERTIE)
		(cond 
			((Said 'examine/people')
				(Print 217 20)
			)
			((Said 'examine,converse/person')
				(Print 217 21)
			)
			((Said 'converse/people')
				(Print 217 22)
			)
			((Said 'ask[/gertie]/attorney<about')
				(= global212 1)
				(= global209 event)
				(proc243_1 4 217 23)
			)
			((Said 'converse/gertie')
				(switch gertieTalkCount
					(0 (Say 1 217 24))
					(1 (Say 1 217 25))
					(2 (Say 1 217 26))
					(else  (Say 1 217 27))
				)
				(++ gertieTalkCount)
			)
			((and (MousedOn self event shiftDown) (not (& global207 $0001)))
				(event claimed: TRUE)
				(ParseName {gertie})
			)
			(
				(and
					(& global207 $0001)
					(or (MousedOn self event shiftDown) (Said 'examine/gertie'))
				)
				(Print 217 28)
				(event claimed: TRUE)
			)
		)
	)
)

(instance Clarence of Prop
	(properties
		y 74
		x 171
		view 401
		loop 1
	)
	
	(method (handleEvent event)
		(= theTalker talkCLARENCE)
		(cond 
			((Said 'converse/attorney')
				(switch clarenceTalkCount
					(0 (Say 1 217 29))
					(1 (Say 1 217 30))
					(2 (Say 1 217 31))
					(else  (Say 1 217 32))
				)
				(++ clarenceTalkCount)
			)
			((Said 'ask[/attorney]/gertie<about')
				(= global212 1)
				(= global209 event)
				(proc243_1 9 217 33)
			)
			((and (MousedOn self event shiftDown) (not (& global207 $0040)))
				(event claimed: TRUE)
				(ParseName {clarence})
			)
			(
				(and
					(& global207 $0040)
					(or (MousedOn self event shiftDown) (Said 'examine/attorney'))
				)
				(event claimed: TRUE)
				(Print 217 20)
			)
		)
	)
)

(instance ClarAss of Prop
	(properties
		y 102
		x 168
		view 401
		cel 2
	)
)

(instance Smoke of Prop
	(properties
		y 60
		x 167
		view 401
		loop 6
	)
)

(instance gBlock of Block
	(properties
		top 98
		left 115
		bottom 104
		right 140
	)
)
