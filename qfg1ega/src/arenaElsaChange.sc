;;; Sierra Script 1.0 - (do not remove this comment)
(script# 172)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rmBrig 0
)

(local
	local0
	[local1 20]
	[local21 20]
	[local41 20] = [2 4 3 3 2 4 2 2 3 4 1 1 3 4 4 4 4 1 3 1]
	[local61 40] = [156 6 119 25 99 50 79 74 100 102 119 94 107 145 140 173 140 153 170 162 193 175 189 133 211 105 213 59 198 37 181 19 144 35 170 35 140 85 176 125]
)
(procedure (localproc_000c)
	(= local0 0)
	(while (< local0 20)
		(= [local1 local0] (Clone star))
		([local1 ([local21 (= [local21 local0] (Clone starScriptOn))]
			register: [local41 local0]
		)]
			cel: 0
			init:
			setPri: 15
			posn: [local61 (* local0 2)] [local61 (+ (* local0 2) 1)]
			setScript: [local21 local0]
		)
		(++ local0)
	)
)

(procedure (localproc_0070)
	(= local0 0)
	(while (< local0 20)
		(= [local1 local0] (Clone star))
		([local1 ([local21 (= [local21 local0] (Clone starScriptOff))]
			register: [local41 local0]
		)]
			cel: (- [local41 local0] 1)
			init:
			setPri: 15
			posn: [local61 (* local0 2)] [local61 (+ (* local0 2) 1)]
			setScript: [local21 local0]
		)
		(++ local0)
	)
)

(procedure (localproc_00db)
	(= local0 19)
	(while (>= local0 0)
		([local21 local0] dispose:)
		([local1 local0] dispose:)
		(-- local0)
	)
)

(instance rmBrig of Room
	(properties
		picture 400
		style $0007
	)
	
	(method (init)
		(Load VIEW vBrigandLeaderFight)
		(Load VIEW vArenaDazzle)
		(Load SOUND 70)
		(super init:)
		(if (Btst SAVED_ELSA)
			(leader init: setScript: leaderToElsa)
		else
			(leader init: setScript: leaderWait)
		)
	)
	
	(method (handleEvent event)
		(switch (event type?)
			(saidEvent
				(cond 
					(
						(or
							(Said
								'cast,use,throw,splash/disenchant,potion[<disenchant]'
							)
							(Said 'disenchant')
						)
						(if (ego has: iDisenchant)
							(Bset SAVED_ELSA)
							(ego use: iDisenchant)
							(leader setScript: leaderToElsa)
							(CenterPrint 172 0)
							;You throw the Dispel Potion on the brigand leader.
						else
							(CenterPrint 172 1)
							;Good idea, but you don't have that potion.
						)
					)
					((Said 'look>')
						(if
						(or (Said '[<at,around][/!*]') (Said '/man,female'))
							(CenterPrint 172 2)
							;You see the brigand leader.  From the look in her eyes you've only got seconds to live.
						)
					)
					((Said 'cast')
						(CenterPrint 172 3)
						;Casting a spell isn't useful now.
						)
					((Said 'ask')
						(CenterPrint 172 4)
						;She's not in a talking mood.
						)
					((Said 'fight')
						(CenterPrint 172 5)
						;Not a good idea.
						)
					(else (event claimed: 1)
						(CenterPrint 172 6)
						;No time for that.
						)
				)
			)
		)
		(super handleEvent: event)
	)
)

(instance leaderToElsa of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(SolvePuzzle POINTS_DISPELBRIGANDLEADER 35)
				(SetCursor theCursor FALSE)
				(elsaChange init: play:)
				(localproc_000c)
				(= cycles 20)
			)
			(1
				(leader setCel: 1)
				(= cycles 20)
			)
			(2
				(localproc_00db)
				(localproc_0070)
				(= cycles 20)
			)
			(3
				(localproc_00db)
				(= cycles 2)
			)
			(4 (curRoom newRoom: 97))
		)
	)
)

(instance leaderWait of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(theGame setCursor: normalCursor (HaveMouse))
				(= seconds 3)
			)
			(2
				(CenterPrint 172 7)
				;The Brigand leader looks tough!  This may be the toughest battle of your career so far.
				(= seconds 3)
			)
			(3 (curRoom newRoom: 97))
		)
	)
)

(instance starScriptOn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles (* register 3)))
			(1 (client setCycle: EndLoop self))
			(2
				(client loop: 1 cel: 0 setCycle: Forward)
			)
		)
	)
)

(instance starScriptOff of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client loop: 1 cel: 0 setCycle: Forward)
				(= cycles (* register 3))
			)
			(1
				(client loop: 0 cel: 6 setCycle: BegLoop)
			)
		)
	)
)

(instance leader of Prop
	(properties
		y 188
		x 160
		view vBrigandLeaderFight
	)
)

(instance star of Prop
	(properties
		y 95
		x 107
		view vArenaDazzle
	)
)

(instance elsaChange of Sound
	(properties
		number 70
		priority 3
	)
)
