;;; Sierra Script 1.0 - (do not remove this comment)
(script# 117)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Menu)
(use Actor)
(use System)

(public
	rm117 0
)

(local
	[local0 2]
	saveBits
	saveBits2
)
(procedure (DoDisplay message)
	(= saveBits2
		(Display message
			p_width 250
			p_at 35 120
			p_mode teJustCenter
			p_font 300
			p_color vLGREEN
			p_save
		)
	)
	(RedrawCast)
)

(procedure (RestoreDisplay)
	(Display 117 1 p_restore saveBits2)
	(RedrawCast)
)

(instance rm117 of Room
	(properties
		picture 83
	)
	
	(method (init)
		(= showStyle HSHUTTER)
		(HandsOff)
		(= inCartoon TRUE)
		(TheMenuBar state: TRUE draw:)
		(StatusLine enable:)
		(Load VIEW 54)
		(Load VIEW 139)
		(Load SOUND 81)
		(super init:)
		(ship init:)
		(self setScript: shipScript)
	)
)

(instance shipScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits
					(Display 117 0
						p_width 250
						p_at 35 25
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(theMusic number: 81 loop: -1 play:)
				(= cycles 2)
			)
			(1
				(ship setMotion: MoveTo 164 110 self)
			)
			(2
				(ship cycleSpeed: 0 setCycle: EndLoop self)
			)
			(3
				(ship cel: 0 setLoop: 1 setCycle: EndLoop self)
			)
			(4
				(= seconds 2)
			)
			(5
				(Display 117 1 p_restore saveBits)
				(curRoom setScript: endScript)
			)
		)
	)
)

(instance endScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(end init: setCycle: EndLoop self)
			)
			(1
				(= seconds 5)
			)
			(2
				(end setCel: 255 setMotion: MoveTo 154 45 self)
			)
			(3
				(end stopUpd:)
				(= seconds 3)
			)
			(4
				(= saveBits
					(Display
						{Thanks To The Following For Their\n
						Cooperation In The Making Of This Game:}
						p_width 250
						p_at 35 85
						p_mode teJustCenter
						p_font 300
						p_color vLGREEN
						p_save
					)
				)
				(= seconds 3)
			)
			(5
				(DoDisplay {Pestulon Department of Forestry})
				(= seconds 6)
			)
			(6
				(RestoreDisplay)
				(DoDisplay {Monolith Synthetic Industries, Inc.})
				(= seconds 6)
			)
			(7
				(RestoreDisplay)
				(DoDisplay {Fester's World-O-Wonders})
				(= seconds 6)
			)
			(8
				(RestoreDisplay)
				(DoDisplay {Gippazoid Novelties})
				(= seconds 6)
			)
			(9
				(RestoreDisplay)
				(DoDisplay {Phleebhut Sand Advisory Council})
				(= seconds 6)
			)
			(10
				(RestoreDisplay)
				(DoDisplay {Arnoid Droidworks})
				(= seconds 6)
			)
			(11
				(RestoreDisplay)
				(DoDisplay {Caffeinate 90})
				(= seconds 6)
			)
			(12
				(RestoreDisplay)
				(DoDisplay {OrboSnack Food Inhalers})
				(= seconds 6)
			)
			(13
				(RestoreDisplay)
				(DoDisplay {Friends of the Talking Bear Society})
				(= seconds 6)
			)
			(14
				(RestoreDisplay)
				(DoDisplay
					{Mark Seibert, Bob Siebenberg\n& Stuart Goldstein\nFor Their Outstanding Sound Work}
				)
				(= seconds 6)
			)
			(15
				(RestoreDisplay)
				(DoDisplay
					{Doug Oldfield, Ken Koch & Chris Smith\nFor A Great Programming Effort}
				)
				(= seconds 6)
			)
			(16
				(Display 117 1 p_restore saveBits)
				(Display 117 1 p_restore saveBits2)
				(RedrawCast)
				(= saveBits
					(Display
						{The Little People, Nobodies, Scum\nWould Also Like To Thank\n}
						p_width 250
						p_at 35 85
						p_mode teJustCenter
						p_font 300
						p_color vLGREEN
						p_save
					)
				)
				(= saveBits2
					(Display
						{Robert E. "Bobbit" Heitman\n
						For His Generous, Yet Verbose, Contribution of Advice, Help & Emergency Code Service}
						p_width 250
						p_at 35 120
						p_mode teJustCenter
						p_font 300
						p_color vLGREEN
						p_save
					)
				)
				(= seconds 6)
			)
			(17
				(Display 117 1 p_restore saveBits)
				(RedrawCast)
				(Display 117 1 p_restore saveBits2)
				(RedrawCast)
				(= saveBits
					(Display
						{Very Special Thanks to}
						p_width 250
						p_at 35 95
						p_mode teJustCenter
						p_font 300
						p_color vLGREEN
						p_save
					)
				)
				(RedrawCast)
				(= saveBits2
					(Display
						{The Two Babes From Andromeda\n
						(Our Wives)\n
						For Putting Up With Us These Last 12 Months}
						p_width 250
						p_at 35 120
						p_mode teJustCenter
						p_font 300
						p_color vLGREEN
						p_save
					)
				)
				(= seconds 8)
			)
			(18
				(Display 117 1 p_restore saveBits2)
				(RedrawCast)
				(= saveBits2
					(Display
						{You!\n(For Shelling Out Your Hard Earned Bucks To Buy This Game)}
						p_width 250
						p_at 35 120
						p_mode teJustCenter
						p_font 300
						p_color vLGREEN
						p_save
					)
				)
				(= seconds 6)
			)
			(19
				(Display 117 1 p_restore saveBits)
				(Display 117 1 p_restore saveBits2)
				(RedrawCast)
				(self changeState: 4)
			)
		)
	)
)

(instance ship of Actor
	(method (init)
		(super init:)
		(self
			view: 54
			setLoop: 0
			setCel: 0
			posn: 164 179
			setStep: 1 1
			ignoreActors: TRUE
			illegalBits: 0
			setCycle: 0
		)
	)
)

(instance end of Actor
	(properties
		view 139
	)
	
	(method (init)
		(super init:)
		(self posn: 154 103 cel: 0 cycleSpeed: 1)
	)
)
