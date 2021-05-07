;;; Sierra Script 1.0 - (do not remove this comment)
(script# 277)
(include sci.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rudypet 0
)
(synonyms
	(rudolph person fellow)
)

(local
	local0
	local1
	local2
)
(instance Dog of Prop
	(properties
		y 147
		x 108
		view 520
		loop 3
	)
	
	(method (handleEvent event)
		(cond 
			(
				(or
					(Said 'feed,deliver/*/beauregard>')
					(Said 'feed,deliver/*<beauregard>')
				)
				(cond 
					((and (ego has: 12) (Said '/bone')) (Print 277 0))
					((and theInvItem haveInvItem) (Print 277 1))
					(else (DontHave))
				)
				(event claimed: 1)
			)
			(
				(or
					(Said 'deliver,hold/*/beauregard')
					(Said 'deliver,hold/*<beauregard')
				)
				(if (and theInvItem haveInvItem)
					(Print 277 2)
				else
					(DontHave)
				)
			)
			((Said 'throw/bone') (Print 277 3))
			((Said '/beauregard>')
				(cond 
					((Said 'examine') (Print 277 4))
					((Said 'get,move,drag,get') (Print 277 5))
					((Said 'pat') (Print 277 6))
					((Said 'converse') (Print 277 7))
					((Said 'kill') (Print 277 8))
					((Said 'kiss') (Print 277 9))
				)
			)
		)
	)
)

(instance rudypet of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(Load rsFONT 4)
		(= global195 256)
		(if (not (& global118 $0004))
			(Load rsFONT 41)
			(Load rsVIEW 642)
			(LoadMany 132 29 94 95 96)
			(Load rsSCRIPT 406)
		)
		(Dog init:)
		(Rudy init:)
		(self setScript: petDog)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(super handleEvent: event)
		(if (event claimed?) (return 1))
		(if (== (event type?) evSAID)
			(cond 
				((Said 'examine/rudolph') (Print 277 10))
				((Said 'feed/food') (Print 277 1))
			)
		)
		(return (super handleEvent: event))
	)
)

(instance petDog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(and
						(== prevRoomNum 31)
						(or (!= (ego x?) 118) (!= (ego y?) 144))
					)
					(= state -1)
				)
				(= cycles 1)
			)
			(1
				(cond 
					((not global216) (= state 0))
					((not (& global118 $0004))
						(= global118 (| global118 $0004))
						(self setScript: (ScriptID 406 0))
						(= state 0)
					)
					((self script?) (= state 0))
				)
				(= cycles 1)
			)
			(2
				(Rudy cycleSpeed: 1 loop: 1 cel: 2 setCycle: Beg self)
			)
			(3
				(Rudy loop: 0 setCycle: Fwd)
				(Dog setCycle: Fwd)
				(= seconds (Random 3 5))
			)
			(4
				(Dog setCycle: 0)
				(Rudy loop: 1 cel: 0 setCycle: End)
				(= state 1)
				(= seconds (Random 6 12))
			)
		)
	)
)

(instance Rudy of Prop
	(properties
		y 147
		x 133
		view 390
		loop 1
	)
	
	(method (handleEvent event)
		(= theTalker 9)
		(cond 
			((Said 'ask//*<about')
				(switch local1
					(0 (Say 1 277 11) (++ local1))
					(1 (Print 277 12))
				)
			)
			((Said 'tell//*<about')
				(switch local2
					(0 (Say 1 277 13) (++ local2))
					(1 (Print 277 14))
				)
			)
			(
				(or
					(Said 'deliver/*[/rudolph]')
					(Said 'deliver/*[<rudolph]')
				)
				(event claimed: 1)
				(if (and theInvItem haveInvItem)
					(Print 277 15)
				else
					(DontHave)
				)
			)
			(
			(or (Said 'hold/*/[rudolph]') (Said 'hold/*[<rudolph]'))
				(event claimed: 1)
				(if (and theInvItem haveInvItem)
					(Print 277 16)
				else
					(DontHave)
				)
			)
			((Said '/rudolph>')
				(cond 
					((Said 'hear') (Print 277 17))
					((Said 'get') (Print 277 18))
					((Said 'kill') (Print 277 19))
					((Said 'kiss') (Print 277 20))
					((Said 'embrace') (Print 277 20))
					((Said 'converse')
						(= theTalker 9)
						(switch local0
							(0 (Say 1 277 21))
							(1 (Say 1 277 22))
							(2 (Say 1 277 23))
							(3 (Say 1 277 24))
							(else  (Print 277 25))
						)
						(++ local0)
					)
				)
			)
			((Said 'flirt//rudolph') (Print 277 26))
		)
	)
)
