;;; Sierra Script 1.0 - (do not remove this comment)
(script# 98)
(include game.sh)
(use Main)
(use Timer)
(use Game)
(use Actor)
(use System)

(public
	rm98 0
)

(local
	saveBits
)
(instance rm98 of Room
	(properties
		picture 121
		style WIPEDOWN
	)
	
	(method (init)
		(HandsOff)
		(Load VIEW 54)
		(Load PICTURE 201)
		(Load PICTURE 202)
		(Load PICTURE 203)
		(Load PICTURE 204)
		(Load PICTURE 205)
		(Load PICTURE 206)
		(Load PICTURE 207)
		(Load SOUND 93)
		(spaceShip init:)
		(super init:)
		(self setScript: startShip)
	)
)

(instance startShip of Script
	(method (doit)
		(if (and (== (self state?) 3) (== (theMusic prevSignal?) 10))
			(self cue:)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits
					(Display
						{You enter a blackness like no other you\n
						have ever experienced. All sense of time\n
						and speed are lost.}
						p_width 250
						p_at 35 80
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(Timer setReal: self 10)
			)
			(1
				(Display 98 0 p_restore saveBits)
				(= saveBits
					(Display
						{Suddenly...}
						p_width 250
						p_at 35 95
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(Timer setReal: self 3)
			)
			(2
				(Display 98 0 p_restore saveBits)
				(= saveBits
					(Display
						{A bright light becomes visible in the\n
						distance. It grows larger as your ship\n
						races toward it. Finally you are hurled\n
						out of the blackness into a parallel universe.}
						p_width 250
						p_at 35 70
						p_mode teJustCenter
						p_font 300
						p_color vYELLOW
						p_save
					)
				)
				(Timer setReal: self 10)
			)
			(3)
			(4
				(Display 98 0 p_restore saveBits)
				(spaceShip show:)
				(curRoom overlay: 201 WIPEDOWN)
				(Timer setCycle: self 1)
			)
			(5
				(curRoom overlay: 202 WIPEDOWN)
				(Timer setCycle: self 1)
			)
			(6
				(curRoom overlay: 203 WIPEDOWN)
				(Timer setCycle: self 1)
			)
			(7
				(curRoom overlay: 204 WIPEDOWN)
				(Timer setCycle: self 1)
			)
			(8
				(curRoom overlay: 205 WIPEDOWN)
				(Timer setCycle: self 1)
			)
			(9
				(curRoom overlay: 206 WIPEDOWN)
				(Timer setCycle: self 1)
			)
			(10
				(curRoom overlay: 207 WIPEDOWN)
				(Timer setCycle: self 1)
			)
			(11
				(if (< (spaceShip cel?) (spaceShip lastCel:))
					(spaceShip
						setCel: (+ (spaceShip cel?) 1)
						posn: (spaceShip x?) (- (spaceShip y?) 2)
					)
					(-- state)
					(Timer setCycle: self 1)
				else
					(self cue:)
				)
			)
			(12
				(spaceShip hide:)
				(Timer setReal: self 2)
			)
			(13
				(curRoom newRoom: 115)
			)
		)
	)
)

(instance spaceShip of Prop
	(properties
		view 54
		priority 15
	)
	
	(method (init)
		(super init:)
		(self posn: 154 100 hide:)
	)
)
