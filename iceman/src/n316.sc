;;; Sierra Script 1.0 - (do not remove this comment)
(script# 316)
(include game.sh)
(use Main)

(public
	RemoveInvItems 0
	IsInvItemInRoom 1
)

(procedure (RemoveInvItems where who &tmp i what)
	(for ((= i 0)) (< i (- argc 1)) ((++ i))
		(if (not ((= what (inventory at: [who i])) owner?))
			(what owner: where)
		)
	)
)

(procedure (IsInvItemInRoom where what &tmp i)
	(return
		(if ((= i (inventory at: where)) ownedBy: where)
			i
		else
			0
		)
	)
)
