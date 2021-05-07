;;; Sierra Script 1.0 - (do not remove this comment)
(script# 202)
(include sci.sh)
(use Main)
(use Intrface)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	EthelDrunk 0
)
(synonyms
	(ethel person girl)
)

(local
	[local0 24] = [160 185 160 132 65 129 242 187 197 172 233 134 189 138 80 138 80 113 136 177 135 163]
	[local24 24] = [0 0 160 132 83 42 340 187 340 170 304 97 159 113 -20 138 -20 138 166 240 118 240]
	[local48 24] = [160 240 -20 156 -20 62 -20 187 104 240 156 240 340 139 340 130 193 143 41 113 340 140]
	[local72 12] = [0 14 21 27 26 25 13 9 3 10 4 5]
	local84
	local85
	local86
	[local87 5]
)
(instance Smashed of Sound
	(properties)
)

(instance EthelDrunk of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(if (not (& global118 $0004))
			(LoadMany 135 41)
			(LoadMany 132 29 94 95 96)
			(Load rsVIEW 642)
			(Load rsSCRIPT 406)
		)
		(Load rsVIEW 903)
		(LoadMany 143 243 275)
		(= [global377 3] 275)
		(if (== [global368 0] 0)
			(= [global368 0] 1099)
			(= global113 0)
		)
		(if (< [global368 0] 2)
			(= [global368 0] 900)
			(= global113 2)
			(= [local24 4] 40)
			(= [local24 5] 240)
		)
		(if (== curRoomNum 25) (Ethel setLoop: 0))
		(if
			(and
				(== [local72 (- 11 global113)] curRoomNum)
				(> [global368 0] (* (- 10 global113) 100))
			)
			(Smashed number: 62 loop: -1 priority: 12 play:)
			(= local84 1)
			(= global208 (| global208 $0008))
			(Ethel
				setCycle: Walk
				ignoreHorizon: 1
				moveSpeed: 2
				cycleSpeed: 1
				setAvoider: ((Avoid new:) offScreenOK: 1)
				posn: [local0 (* global113 2)] [local0 (+ (* global113 2) 1)]
				init:
			)
			(if (== curRoomNum 13) (Ethel observeControl: 64))
			(if (== curRoomNum 27)
				(Ethel setMotion: MoveTo 193 143)
			else
				(Ethel
					setMotion:
						MoveTo
						[local48 (* global113 2)]
						[local48 (+ (* global113 2) 1)]
				)
			)
			(= gCurRoomNum_2 curRoomNum)
			(self setScript: ethelActions)
		)
	)
	
	(method (doit)
		(if (and (< [global368 0] 2) (not local84))
			(= [global368 0] 900)
			(= global113 2)
			(= [local24 4] 40)
			(= [local24 5] 240)
		)
		(if
			(and
				(!= curRoomNum 4)
				(== gCurRoomNum_4 0)
				(== (mod [global368 0] 100) 0)
				(== [local72 (/ [global368 0] 100)] curRoomNum)
				(not local84)
			)
			(if (User controls?)
				(Smashed number: 62 loop: -1 priority: 12 play:)
				(DisposeScript 990)
				(= global113 (- 11 (/ [global368 0] 100)))
				(= global208 (| global208 $0008))
				(= local84 1)
				(Ethel
					setCycle: Walk
					ignoreHorizon: 1
					moveSpeed: 2
					cycleSpeed: 1
					setAvoider: ((Avoid new:) offScreenOK: 1)
					posn: [local24 (* global113 2)] [local24 (+ (* global113 2) 1)]
					init:
				)
				(Ethel observeControl: 64)
				(if (== curRoomNum 27)
					(Ethel setMotion: MoveTo 193 143)
				else
					(Ethel
						setMotion:
							MoveTo
							[local48 (* global113 2)]
							[local48 (+ (* global113 2) 1)]
					)
				)
				(= gCurRoomNum_2 curRoomNum)
				(self setScript: ethelActions)
			else
				(= [global368 0] (+ [global368 0] 10))
			)
		)
		(super doit:)
	)
	
	(method (dispose)
		(if (and (== global113 0) (== curRoomNum 5))
			(= global113 1)
		)
		(if local84
			(if
				(or
					(> (Ethel y?) 200)
					(> (Ethel x?) 320)
					(< (Ethel x?) 0)
				)
				(++ global113)
				(= [global368 0] (- 1100 (* global113 100)))
			else
				(= [global368 0] (- 1120 (* global113 100)))
			)
		)
		(DisposeScript 985)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(if
			(and
				(== (event type?) evSAID)
				global208
				(Said
					'ask,tell,hold,deliver,examine,get,kill,kiss,embrace,flirt>'
				)
			)
			(Ethel setScript: (ScriptID 243 0))
			((Ethel script?) handleEvent: event)
			(if (event claimed?) (return))
		)
	)
)

(instance ethelActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== currentAct 3)
					(cond 
						((not global216) (= state -1))
						((not (& global118 $0004))
							(if (and (== gameMinutes 3) (== curRoomNum 10))
								(= local86 1)
								(gDoor startUpd:)
							)
							(= global118 (| global118 $0004))
							(self setScript: (ScriptID 406 0))
							(= state -1)
						)
						((self script?) (= state -1))
					)
				)
				(= cycles 3)
			)
			(1
				(if (and (== curRoomNum 10) local86)
					(= local86 0)
					(gDoor stopUpd:)
				)
				(if
					(and
						(== (Ethel x?) [local48 (* global113 2)])
						(== (Ethel y?) [local48 (+ (* global113 2) 1)])
					)
					(if (== curRoomNum 27) (= state 2))
				else
					(= state 0)
				)
				(= cycles 1)
			)
			(2
				(= local86 0)
				(= local84 0)
				(++ global113)
				(= [global368 0] (- 1100 (* global113 100)))
				(if (< [global368 0] 2)
					(= [global368 0] 900)
					(= global113 2)
				)
				(Smashed fade:)
				(= gCurRoomNum_2 0)
				(Ethel dispose:)
				(= global208 (& global208 $fff7))
				(= [global377 3] 0)
				(client setScript: 0)
			)
			(3
				(= state 1)
				(Ethel setMotion: MoveTo 110 110 self)
			)
		)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
	)
)

(instance Ethel of Act
	(properties
		yStep 3
		view 328
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(= theTalker 4)
		(return
			(if local84
				(cond 
					((Said '/drink,glass>')
						(cond 
							((Said 'examine') (Print 202 0))
							((Said 'get') (Print 202 1))
						)
					)
					((Said 'hear/ethel') (Print 202 2))
					(
					(and (not (& global207 $0008)) (MousedOn self event 3)) (event claimed: 1) (ParseName {ethel}))
					(
						(and
							(& global207 $0008)
							(or (MousedOn self event 3) (Said 'examine/ethel'))
						)
						(event claimed: 1)
						(Print 202 3)
					)
					((Said 'converse/ethel')
						(= theTalker 4)
						(switch local85
							(0 (Say 1 202 4))
							(1 (Say 1 202 5) (Say 1 202 6))
							(2 (Say 1 202 7))
							(3 (Say 1 202 8))
							(4 (Say 1 202 9))
							(5
								(Say 1 202 10)
								(Say 1 202 11)
							)
							(6 (Say 1 202 12))
							(else  (Print 202 13))
						)
						(++ local85)
					)
				)
			else
				0
			)
		)
	)
)
