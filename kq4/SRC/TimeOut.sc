;;; Sierra Script 1.0 - (do not remove this comment)
(script# 302)
(include game.sh)
(use Main)
(use Intrface)
(use User)
(use System)

(public
	timeOut 0
)

(instance timeOut of Script	;time's up, game over!
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= isHandsOff TRUE)
				(ego setMotion: 0)
				(cast eachElementDo: #hide)
				(User canControl: 0 canInput: 0)
				(DrawPic 991 DISSOLVE)
				(Timer setReal: self 5)
			)
			(1
				(= globalPrint (Print 302 0 #dispose))
				(Timer setReal: self 15)
			)
			(2
				(if modelessDialog
					(modelessDialog dispose:)
				)
				(= isHandsOff FALSE)
				(= dead TRUE)
			)
		)
	)
)
