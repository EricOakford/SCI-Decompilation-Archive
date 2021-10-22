;;; Sierra Script 1.0 - (do not remove this comment)
(script# DYING)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use Motion)
(use System)

(public
	DyingScript 0
)

(instance DyingScript of Script
	(method (changeState newState &tmp [buf 133])
		(switch (= state newState)
			(0
				(HandsOff)
				(music fade:)
				(curRoom setScript: 0)
				(= currentStatus egoDEAD)
				(Load SOUND 2)
				(= seconds 3)
			)
			(1
				(Load VIEW caller)
				(soundFX stop:)
				(music number: 2 loop: 1 priority: -1 play:)
				(deadIcon view: caller)
				(if
					(Print register
						#title next
						#font bigFont
						#icon deadIcon
						#button {Keep On Muddling} 0
						#button {Order A Hintbook} 1
					)
					(Print 40 0)
				)
				(repeat
					(switch
						(Print 40 1
							#title {Al says:}
							#font bigFont
							#button {Restore} 1
							#button {Restart} 2
							#button {Quit} 3
						)
						(1
							(theGame restore:)
						)
						(2
							(theGame restart:)
						)
						(3
							(= quit TRUE)
							(break)
						)
					)
				)
			)
		)
	)
)

(instance deadIcon of DCIcon
	(method (init)
		(super init:)
		(if (== curRoomNum 540)
			((= cycler (EndLoop new:)) init: self)
		)
	)
)
