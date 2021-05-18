;;; Sierra Script 1.0 - (do not remove this comment)
(script# 281)
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
	rudywand 0
)
(synonyms
	(rudolph person fellow)
)

(local
	local0 = [65 129 242 187 197 172 233 134 189 138 80 138 100 143 136 177 135 163]
	local18 = [0 0 340 187 340 170 304 97 159 113 -20 138 -20 143 66 240 118 240]
	local36 = [-20 62 -20 187 104 240 156 240 340 139 340 130 193 143 340 173 340 140]
	local54 = [22 21 27 26 25 13 9 3 10]
	local63
	talkCount
	askCount
	[local66 5]
)
(instance rudywand of Region
	(properties)
	
	(method (init)
		(super init:)
		(if (and (== currentAct 3) (not (& global118 $0001)))
			(Load FONT 41)
			(LoadMany SOUND 29 94 95 96)
			(Load SCRIPT 406)
			(Load VIEW 642)
		)
		(if (== currentAct 3)
			(LoadMany 143 243 296)
			(= [global377 8] 296)
			(Load VIEW 908)
			(if (== [global368 1] 0)
				(= [global368 1] 800)
				(= global114 0)
			)
		else
			(if (< [global368 1] 2)
				(= [global368 1] 800)
				(= global114 0)
			)
			(= [local54 0] 14)
			(= [local36 14] 41)
			(= [local36 15] 113)
		)
		(Rudy
			view: 380
			loop: 1
			illegalBits: cWHITE
			ignoreHorizon: TRUE
		)
		(if
			(and
				(== [local54 (- 8 global114)] curRoomNum)
				(>= [global368 1] (* (- 8 global114) 100))
			)
			(= gCurRoomNum_4 curRoomNum)
			(|= global208 $0100)
			(= [global368 1] (- 899 (* global114 100)))
			(= local63 1)
			(Rudy
				setCycle: Walk
				setAvoider: ((Avoider new:) offScreenOK: TRUE)
				posn: [local0 (* global114 2)] [local0 (+ (* global114 2) 1)]
				init:
			)
			(if (== curRoomNum 27)
				(Rudy setMotion: MoveTo 193 143 self)
			else
				(Rudy
					setMotion:
						MoveTo
						[local36 (* global114 2)]
						[local36 (+ (* global114 2) 1)]
				)
			)
			(self setScript: rudyActions)
		)
	)
	
	(method (doit)
		(if
			(and
				(== (mod [global368 1] 100) 0)
				(== [local54 (/ [global368 1] 100)] curRoomNum)
				(not local63)
			)
			(if (User controls?)
				(|= global208 $0100)
				(= global114 (- 8 (/ [global368 1] 100)))
				(= gCurRoomNum_4 curRoomNum)
				(= local63 1)
				(Rudy
					setCycle: Walk
					setAvoider: ((Avoider new:) offScreenOK: TRUE)
					posn: [local18 (* global114 2)] [local18 (+ (* global114 2) 1)]
					init:
				)
				(if (== curRoomNum 27)
					(Rudy setMotion: MoveTo 193 143 self)
				else
					(Rudy
						setMotion:
							MoveTo
							[local36 (* global114 2)]
							[local36 (+ (* global114 2) 1)]
					)
				)
				(self setScript: rudyActions)
			else
				(= [global368 0] (+ [global368 0] 10))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if local63
			(= [global368 1] (- 720 (* global114 100)))
		)
		(= gCurRoomNum_4 0)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if
			(and
				global208
				(Said
					'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
				)
			)
			(Rudy setScript: (ScriptID 243 0))
			((Rudy script?) handleEvent: event)
			(if (event claimed?) (return))
		)
	)
)

(instance rudyActions of Script
	
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
					((not global216)
						(= state 0)
					)
					(
					(and (== currentAct 3) (not (& global118 $0001)))
						(if (and (== gameMinutes 3) (== curRoomNum 10))
							(gDoor startUpd:)
						)
						(|= global118 $0001)
						(self setScript: (ScriptID 406 0))
						(= state 0)
					)
					((self script?)
						(= state 0)
					)
				)
				(= cycles 1)
			)
			(2
				(if (== curRoomNum 10)
					(gDoor stopUpd:)
				)
				(if
					(and
						(== (Rudy x?) [local36 (* global114 2)])
						(== (Rudy y?) [local36 (+ (* global114 2) 1)])
					)
					(if (== curRoomNum 27)
						(= state 3)
					)
				else
					(= state 1)
				)
				(= cycles 1)
			)
			(3
				(++ global114)
				(= [global368 1] (- 899 (* global114 100)))
				(if (and (== currentAct 6) (< [global368 1] 2))
					(= [global368 1] 900)
					(= global114 0)
				)
				(if (and (== curRoomNum 21) (== currentAct 3))
					(Rudy dispose:)
				else
					(Rudy setAvoider: 0 hide:)
				)
				(= gCurRoomNum_4 0)
				(DisposeScript 985)
				(&= global208 $feff)
				(= [global377 8] 0)
				(= local63 0)
				(client setScript: 0)
			)
			(4
				(= state 2)
				(Rudy setMotion: MoveTo 110 110 self)
			)
		)
	)
)

(instance Rudy of Actor
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if local63
			(if (== currentAct 6)
				(cond 
					((Said 'ask,tell')
						(= theTalker talkRUDY)
						(if (not askCount)
							(= theTalker talkRUDY)
							(++ askCount)
							(Say 1 281 0)
						else
							(Say 1 281 1)
						)
					)
					((Said 'deliver,hold')
						(Print 281 2)
					)
				)
			)
			(cond 
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
					(Print 281 3)
				)
				((Said 'converse/rudolph>')
					(= theTalker talkRUDY)
					(switch talkCount
						(0
							(Say 1 281 4)
							(= theTalker talkLAURA)
							(Say 1 281 5)
						)
						(1 (Say 1 281 6))
						(2 (Say 1 281 7))
						(else  (Print 281 8))
					)
					(++ talkCount)
					(event claimed: TRUE)
				)
			)
		)
	)
)
