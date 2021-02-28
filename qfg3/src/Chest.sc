;;; Sierra Script 1.0 - (do not remove this comment)
(script# CHEST) ;29
(include game.sh) (include "29.shm")
(use Main)
(use GloryWindow)
(use System)

(public
	doCode 0
	useCode 1
)

(instance aChestWindow of GloryWindow
	(properties
		color 5
		back 2
		yOffset 28
	)
	
	(method (open)
		((ScriptID GLORY_INV 2)
			nsLeft: (- (/ (- (self right?) (self left?)) 2) 60)
		)
		(+= bottom 4)
		(super open:)
	)
)

(instance doCode of Code
	
	(method (init &tmp i gotOne where)
		(= gotOne FALSE)
		(= where
			(cond 
				((== curRoomNum 310) 0)
				((== curRoomNum 430) 14)
				(else 14)
			)
		)
		(for ((= i 0)) (< i 14) ((++ i))
			(if [chestInventory (+ i where)]
				((inventory at: (+ i 10))
					realOwner: (ScriptID curRoomNum 0)
				)
				(= gotOne TRUE)
			)
		)
		(for ((= i 0)) (< i iLastInvItem) ((++ i))
			(if (== ((inventory at: i) realOwner?) curRoomNum)
				((inventory at: i)
					realOwner: (ScriptID curRoomNum 0)
				)
				(= gotOne TRUE)
			)
		)
		(if gotOne
			(inventory window: aChestWindow)
			((ScriptID GLORY_INV 1) init: (ScriptID curRoomNum 0))
			(for ((= i 0)) (< i iLastInvItem) ((++ i))
				(if
					(==
						((inventory at: i) realOwner?)
						(ScriptID curRoomNum 0)
					)
					((inventory at: i) realOwner: curRoomNum)
				)
				(if ((inventory at: i) amount?)
					((inventory at: i) realOwner: ego)
				)
			)
		else
			(messager say: N_CHEST V_DOIT C_EMPTY 1 0 CHEST)
		)
	)
)

(instance useCode of Code

	(method (init what howMany where)
		(cond 
			((or (not what) (== what (+ iLastInvItem 1)))
				(messager say: N_CHEST V_DOIT C_MONEY 1 0 CHEST)
			)
			(((inventory at: what) mustKeep?)
				(messager say: N_CHEST V_DOIT C_ESSENTIAL 1 0 CHEST)
			)
			((or (< what 10) (>= what 24))
				((inventory at: what) loseItem: roomGets:)
				(messager say: N_CHEST V_DOIT C_PUT_SINGLE 1 0 CHEST)
				(theIconBar curInvIcon: 0)
			)
			(else
				(= where
					(cond 
						((== curRoomNum 310) 0)
						((== curRoomNum 430) 14)
						(else 14)
					)
				)
				((inventory at: what) roomGets:)
				((inventory at: what)
					amount: (- ((inventory at: what) amount?) 1)
				)
				(if (> ((inventory at: what) amount?) 0)
					(messager say: N_CHEST V_DOIT C_PUT_MULTIPLE 1 0 CHEST)
				else
					((inventory at: what) loseItem:)
					(theIconBar curInvIcon: 0)
					(messager say: N_CHEST V_DOIT C_PUT_SINGLE 1 0 CHEST)
				)
			)
		)
	)
)
