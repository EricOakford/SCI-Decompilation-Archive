;;; Sierra Script 1.0 - (do not remove this comment)
(script# 140)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use System)

(public
	openFromBelow 0
)

(procedure (HenrySays &tmp [str 200])
	(Format @str &rest)
	(Print @str #at 120 10)
)

(instance openFromBelow of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 140)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Bset HENRY_DOOR_OPEN)
				(client
					view: vHenryOutside
					loop: 2
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop
				)
				(= cycles 20)
			)
			(1
				(HenrySays 140 0)
				;"'ello?"
				(self cue:)
			)
			(2 (client setCycle: BegLoop self))
			(3
				(Bclr HENRY_DOOR_OPEN)
				(client stopUpd: setScript: 0)
			)
		)
	)
)
