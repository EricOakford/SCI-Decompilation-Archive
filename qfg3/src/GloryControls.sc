;;; Sierra Script 1.0 - (do not remove this comment)
(script# COMBAT_CONTROLS) ;56
(include game.sh)
(use GControl)

(public
	GloryControls 0
)

(class GloryControls of GameControls
	(properties
		icon1 0
		icon2 0
		icon3 0
		icon4 0
		icon5 0
		icon6 0
		icon7 0
	)
	
	(method (dispatchEvent event &tmp evtMsg evtType)
		(if (& (= evtType (event type?)) direction)
			(&= evtType $ffbf)
			(switch (event message?)
				(dirNW
					(icon1 select:)
				)
				(dirNE
					(icon2 select:)
				)
				(dirSW
					(icon3 select:)
				)
				(dirSE
					(icon4 select:)
				)
				(dirW
					(icon5 select:)
				)
				(dirE
					(icon6 select:)
				)
				(dirN
					(return TRUE)
				)
				(dirS
					(return TRUE)
				)
				(dirStop
					(if (== evtType keyDown)
						(icon7 select:)
					else
						(return TRUE)
					)
				)
			)
		)
		(= evtMsg (event message?))
		(if (== evtType keyDown)
			(cond 
				((== evtMsg ESC)
					(event message: nullEvt)
					(icon7 select:)
				)
				((== evtMsg `7)
					(icon1 select:)
				)
				((== evtMsg `9)
					(icon2 select:)
				)
				((== evtMsg `1)
					(icon3 select:)
				)
				((== evtMsg `3)
					(icon4 select:)
				)
				((== evtMsg `4)
					(icon5 select:)
				)
				((== evtMsg `6)
					(icon6 select:)
				)
				((or (== evtMsg `5) (== evtMsg INSERT) (== evtMsg `0))
					(icon7 select:)
				)
			)
		)
		(if (== evtType keyUp)
			(self eachElementDo: #highlight 0)
		)
		(return
			(super dispatchEvent: event)
		)
	)
	
	(method (disable theIconNumber &tmp i thisIcon)
		(if argc
			(for ((= i 0)) (< i argc) ((++ i))
				(= thisIcon
					(if (IsObject [theIconNumber i])
						[theIconNumber i]
					else
						(self at: [theIconNumber i])
					)
				)
				(thisIcon signal: (| (thisIcon signal?) DISABLED))
			)
		else
			(|= state DISABLED)
		)
	)
)
