;;; Sierra Script 1.0 - (do not remove this comment)
(script# SCARY_FOREST)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	sForReg 0
)
(synonyms
	(branch arm branch)
)

(local
	treeBlock1
	treeBlock2
)
(instance sForReg of Region
	
	(method (handleEvent event &tmp index)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							((Said '/boulder')
								(Print 509 0)
							)
							((Said '/dirt')
								(Print 509 1)
							)
							((Said '/bush')
								(Print 509 2)
							)
							((or (Said '/sky') (Said '<up'))
								(if (not isNightTime)
									(Print 509 3)
								else
									(Print 509 4)
								)
							)
							((Said '/grass')
								(Print 509 5)
							)
							((Said '/flora')
								(Print 509 6)
							)
							((Said '/blossom')
								(Print 509 7)
							)
							((Said '/forest')
								(Print 509 8)
							)
							((Said '/eye')
								(Print 509 9)
							)
							(else
								(event claimed: FALSE)
							)
						)
					)
					((Said 'get/blossom')
						(Print 509 10)
					)
					((Said 'climb/boulder')
						(Print 509 11)
					)
					((Said 'climb/forest')
						(Print 509 12)
					)
					((Said 'kill/forest')
						(Print 509 13)
					)
					(
						(or
							(Said 'break,hit,chop/branch,branch,branch,forest')
							(Said 'swing,use,wave/ax')
						)
						(cond 
							(choppedScaryTree
								(Print 509 14)
							)
							(
								(and
									(not choppedScaryTree)
									(not ((Inventory at: iAxe) ownedBy: ego))
								)
								(Print 509 15)
							)
							((ego has: iAxe)
								(theGame changeScore: 4)
								(sounds eachElementDo: #stop 0)
								(= choppedScaryTree TRUE)
								(ego setScript: chop)
								(chop changeState: 1)
							)
						)
					)
					((Said 'conceal/forest')
						(Print 509 16)
					)
					((Said 'converse')
						(Print 509 17)
					)
					((Said 'deliver>')
						(if
							(and
								(= index (inventory saidMe:))
								(ego has: (inventory indexOf: index))
							)
							(Print 509 18)
						else
							(event claimed: FALSE)
						)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance chop of Script
	(method (changeState newState)
		(switch (= state newState)
			(1
				(HandsOff)
				(ego view: 86 setCycle: Forward)
				((ScriptID 0 4) setReal: self 4)
			)
			(2
				(ego view: 2 setCycle: Walk)
				(Print 509 19)
				(HandsOn)
				(switch curRoomNum
					(11
						((= treeBlock1 (Block new:))
							left: 212
							top: 103
							right: 224
							bottom: 106
						)
						(ego observeBlocks: treeBlock1)
					)
					(12
						(= treeBlock1 (Block new:))
						(= treeBlock2 (Block new:))
						(treeBlock1 left: 197 top: 112 right: 230 bottom: 117)
						(treeBlock2 left: 170 top: 108 right: 185 bottom: 110)
						(ego observeBlocks: treeBlock1 treeBlock2)
					)
				)
			)
		)
	)
)
