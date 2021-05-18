;;; Sierra Script 1.0 - (do not remove this comment)
(script# 257)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	lillRead 0
)
(synonyms
	(lil person girl)
)

(local
	talkCount
	readCount
)
(procedure (LillPrint)
	(Print &rest
		#at 40 10
		#font 4
		#width 125
		#mode teJustCenter
		#draw
		#dispose
	)
)

(instance lillRead of Region
	
	(method (init)
		(super init:)
		(if (not (& global118 $0001))
			(LoadMany SOUND 29 94 95 96)
			(Load FONT 41)
			(Load SCRIPT 406)
			(Load VIEW 642)
		)
		(Load FONT 4)
		(LoadMany 143 406 243 288)
		(Load VIEW 905)
		(= global208 32)
		(= [global377 5] 288)
		(Lillian init:)
		(LHead init: stopUpd:)
		(Book init: stopUpd:)
		(self setScript: reading)
	)
	
	(method (doit)
		(super doit:)
	)
	
	(method (dispose)
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
							((Said '/book')
								(if (< (ego distanceTo: Lillian) 40)
									(Print 257 0)
								else
									(NotClose)
								)
							)
							((Said '/doll')
								(Print 257 1)
							)
						)
					)
					((Said 'get>')
						(cond 
							((Said '/book')
								(Print 257 2)
							)
							((Said '/doll')
								(Print 257 3)
							)
						)
					)
					((Said 'read/book')
						(if (< (ego distanceTo: Lillian) 40)
							(Print 257 0)
						else
							(NotClose)
						)
					)
				)
			else
				0
			)
		)
	)
)

(instance reading of Script
	
	(method (doit)
		(if (and (== state 1) (== (Lillian cel?) 4))
			(Book hide:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((not global216) (= state -1))
					((not (& global118 $0001))
						(|= global118 $0001)
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
				(Lillian loop: 6 setCycle: BegLoop self)
			)
			(2
				(LHead loop: 5 setCycle: Forward)
				(User canInput: FALSE)
				(switch readCount
					(0 (LillPrint 257 4))
					(1 (LillPrint 257 5))
					(2 (LillPrint 257 6))
					(3 (LillPrint 257 7))
					(4 (LillPrint 257 8))
					(5 (LillPrint 257 9))
					(6 (LillPrint 257 10))
					(7 (LillPrint 257 11))
					(8 (LillPrint 257 12))
					(else  (LillPrint 257 13))
				)
				(if (< readCount 9)
					(++ readCount)
				else
					(= readCount 0)
				)
				(= seconds 4)
			)
			(3
				(LHead loop: 5 setCycle: 0)
				(cls)
				(User canInput: TRUE)
				(= seconds 3)
			)
			(4
				(Lillian loop: 2 setCycle: EndLoop)
				(if (< (Random 1 100) 15) (= state 3))
				(= seconds (Random 6 12))
			)
			(5
				(LHead loop: 3 setCycle: EndLoop)
				(= seconds (Random 3 8))
			)
			(6
				(LHead setCycle: BegLoop)
				(if (< (Random 1 100) 25)
					(= state 3)
				else
					(= state 1)
				)
				(= seconds (Random 3 8))
			)
		)
	)
)

(instance Lillian of Actor
	(properties
		y 141
		x 187
		view 506
		loop 6
		cel 5
		illegalBits $0000
	)
	
	(method (handleEvent event)
		(cond 
			((Said 'converse/lil')
				(= theTalker talkLILLIAN)
				(switch talkCount
					(0 (Say 1 257 14))
					(1 (Say 1 257 15))
					(2 (Say 1 257 16))
					(else  (Print 257 17))
				)
				(++ talkCount)
			)
			((Said 'tell,ask/lil')
				(Print 257 18)
			)
			((Said 'deliver,hold/lil')
				(Print 257 19)
			)
			((Said 'hear/lil')
				(Print 257 20)
			)
			((Said 'get/lil')
				(Print 257 21)
			)
			((Said 'kill/lil')
				(Print 257 22)
			)
			((Said 'kiss/lil')
				(Print 257 23)
			)
			((Said 'embrace/lil')
				(Print 257 24)
			)
			(
				(and
					(not (& global207 $0020))
					(or (MousedOn self event shiftDown) (Said 'examine/lil'))
				)
				(|= global207 $0020)
				(= theTalker talkLILLIAN)
				(event claimed: TRUE)
				(Say 0 257 25)
			)
			(
				(and
					(& global207 $0020)
					(or (MousedOn self event shiftDown) (Said 'examine/lil'))
				)
				(event claimed: TRUE)
				(Print 257 26)
			)
		)
	)
)

(instance LHead of Prop
	(properties
		y 141
		x 189
		z 33
		view 506
		loop 3
	)
)

(instance Book of Prop
	(properties
		y 123
		x 177
		view 163
		loop 2
		cel 2
	)
)
