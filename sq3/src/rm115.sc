;;; Sierra Script 1.0 - (do not remove this comment)
(script# 115)
(include game.sh)
(use Main)
(use Timer)
(use Game)
(use Actor)
(use System)

(public
	rm115 0
)

(local
	saveBits
	local1
	local2
	local3
	local4
)
(instance rm115 of Room
	(properties
		picture 115
		style HSHUTTER
	)
	
	(method (init)
		(HandsOff)
		(Load VIEW 801)
		(Load SOUND 93)
		(super init:)
		(self setScript: startShip)
	)
)

(instance startShip of Script
	(method (doit)
		(if (and (== (self state?) 1) (== (theMusic prevSignal?) 20))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits
					(Display
						{You cut the engines to sub-lightspeed as\n
						you near a seemingly habitable planet.}
						p_width 250
						p_at 35 140
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(Timer setReal: self 7)
			)
			(1)
			(2
				(Display 115 0 p_restore saveBits)
				(Timer setReal: self 2)
			)
			(3
				(spaceShip init:)
			)
			(4
				(Timer setReal: self 2)
			)
			(5
				(curRoom newRoom: 116)
			)
		)
	)
)

(instance spaceShip of Prop
	(properties
		view 52
		priority 13
	)
	
	(method (init)
		(super init:)
		(= local1 120)
		(= local2 6)
		(= local3 90)
		(= local4 5)
	)
	
	(method (doit)
		(super doit:)
		(if (< local1 local2)
			(self dispose:)
			(startShip cue:)
		else
			(-= local1 local2)
			(= local3 (mod (+ local3 local4 360) 360))
			(self
				posn: (+ 190 (CosMult local3 local1)) (+ 50 (SinMult local3 local1))
			)
			(if (and (< local1 100) (< cel (self lastCel:)))
				(++ cel)
			)
			(self stopUpd:)
		)
	)
)
