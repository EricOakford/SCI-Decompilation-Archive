;;; Sierra Script 1.0 - (do not remove this comment)
(script# LLROOM)
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use Motion)
(use Game)
(use User)
(use System)

(public
	eRS 0
	lRS 1
)

(procedure (localproc_0333)
	(cond 
		((< (ego x?) 0) (ego x: (+ 0 (* (ego xStep?) 2))))
		((> (ego x?) 319) (ego x: (- 319 (* (ego xStep?) 2))))
	)
)

(procedure (localproc_0379)
	(cond 
		((< (ego y?) (curRoom horizon?)) (ego y: (+ (curRoom horizon?) (* (ego yStep?) 2))))
		((> (ego y?) 189) (ego y: (- 189 (* (ego yStep?) 2))))
	)
)

(instance roomControls of Controls
	(properties
		name {controls}
	)
)

(class LLRoom of Room
	
	(method (init &tmp theWidth theHeight)
		(= number curRoomNum)
		(= controls roomControls)
		(= perspective picAngle)
		(if picture (self drawPic: picture))
		(cond 
			((not (cast contains: ego)) FALSE)
			(script FALSE)
			((not (ego normal?)) FALSE)
			((OneOf style SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
				(= theWidth
					(+
						1
						(/
							(CelWide
								((User alterEgo?) view?)
								((User alterEgo?) loop?)
								((User alterEgo?) cel?)
							)
							2
						)
					)
				)
				(= theHeight
					(+
						1
						(/
							(CelHigh
								((User alterEgo?) view?)
								((User alterEgo?) loop?)
								((User alterEgo?) cel?)
							)
							2
						)
					)
				)
				(switch ((User alterEgo?) edgeHit?)
					(1 ((User alterEgo?) y: 188))
					(4
						((User alterEgo?) x: (- 319 theWidth))
					)
					(3
						((User alterEgo?) y: (+ horizon theHeight))
					)
					(2
						((User alterEgo?) x: (+ 0 theWidth))
					)
				)
				((User alterEgo?) edgeHit: 0 userSpeed:)
				(HandsOn)
			)
			(else (self setScript: eRS 0 prevRoomNum))
		)
	)
	
	(method (doit &tmp dir)
		(cond 
			(script (script doit:))
			((not (cast contains: ego)) FALSE)
			(
				(= dir
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self setScript: lRS 0 dir)
			)
		)
	)
)

(instance lRS of Script

	(method (changeState newState &tmp temp0 theWidth)
		(switch (= state newState)
			(0
				(HandsOff)
				(= theWidth (CelWide (ego view?) (ego loop?) (ego cel?)))
				(switch register
					((client north?)
						(curRoom newRoom: register)
					)
					((client south?)
						(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
						(ego setMotion: PolyPath (ego x?) (+ 189 temp0) self)
					)
					((client east?)
						(ego setMotion: PolyPath (+ 319 theWidth) (ego y?) self)
					)
					((client west?)
						(ego setMotion: PolyPath (- 0 theWidth) (ego y?) self)
					)
				)
			)
			(1
				(ego hide:)
				(= cycles 2)
			)
			(2
				(curRoom setScript: 0 newRoom: register)
			)
		)
	)
)

(instance eRS of Script
	
	(method (changeState newState &tmp temp0 temp1)
		(switch (= state newState)
			(0
				(= cycles 0)
				(HandsOff)
				(= temp0 (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(= temp1 (CelWide (ego view?) (ego loop?) (ego cel?)))
				(switch register
					((client north?)
						(localproc_0333)
						(ego y: (+ (curRoom horizon?) (ego yStep?)))
						(= cycles 1)
					)
					((client south?)
						(localproc_0333)
						(ego
							y: (+ 189 temp0)
							setMotion: MoveTo (ego x?) (- 189 (* (ego yStep?) 2)) self
						)
					)
					((client east?)
						(localproc_0379)
						(ego
							x: (+ 319 (/ temp1 2))
							setMotion: MoveTo (- 319 (+ (* (ego xStep?) 2) 10)) (ego y?) self
						)
					)
					((client west?)
						(localproc_0379)
						(ego
							x: (- 0 (/ temp1 2))
							setMotion: MoveTo (+ 0 (* (ego xStep?) 2) 10) (ego y?) self
						)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(ego userSpeed:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
