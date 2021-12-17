;;; Sierra Script 1.0 - (do not remove this comment)
(script# CAST)
(include game.sh)
(use EventHandler)


(class Cast kindof EventHandler
	(properties
		plane		0		; plane we belong to
	)
	(method (dispose)
		(if plane
			((plane casts?) delete: self)
			(= plane 0)
		)
		(super dispose:)
	)
)