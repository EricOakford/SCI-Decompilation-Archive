;;; Sierra Script 1.0 - (do not remove this comment)
(script# FPROOM)
(include game.sh)
(use Main)
(use PolyPath)
(use Game)
(use User)
(use System)

(public
	eRS 0
)

(local
	local0
)
(procedure (localproc_0490)
	(cond 
		((< (ego x?) 0)
			(ego x: (+ 0 (* (ego xStep?) 2)))
		)
		((> (ego x?) 319)
			(ego x: (- 319 (* (ego xStep?) 2)))
		)
	)
)

(procedure (localproc_04d6)
	(cond 
		((< (ego y?) (curRoom horizon?))
			(ego y: (+ (curRoom horizon?) (* (ego yStep?) 2)))
		)
		((> (ego y?) 189)
			(ego y: (- 189 (* (ego yStep?) 2)))
		)
	)
)

(class FPRoom of Room
	
	(method (init param1 &tmp theX theY [temp2 2])
		(if (== global119 9)
			(cond 
				(
					(OneOf curRoomNum
						410 600 610 620 630 640 650
						660 670 690 710 720 730
					)
					(Bclr 70)
					(timers delete: (ScriptID 0 14))
				)
				((not (timers contains: (ScriptID 0 14)))
					((ScriptID 0 14) setReal: (ScriptID 0 14) 40)
				)
			)
		)
		(= number curRoomNum)
		(= perspective picAngle)
		(if (Btst 1)
			(PalVary PALVARYKILL)
		)
		(if picture
			(self drawPic: picture)
		)
		(if
			(and
				(OneOf curRoomNum
					200 210 220 230 235 240 250 260
					265 270 300 310 320
				)
				(!= numColors 16)
			)
			(SetCursor 0)
			(SetVideoMode 2)
			(SetCursor 1)
		else
			(SetVideoMode 0)
		)
		(cond 
			((== curRoomNum 620)
				(if (!= prevRoomNum 610)
					0
				else
					(self setScript: eRS param1 prevRoomNum)
				)
			)
			((not (cast contains: ego)) 0)
			(script 0)
			((not ((User alterEgo?) edgeHit?)) 0)
			((OneOf style SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
				(= theX
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
				(= theY
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
						((User alterEgo?) x: (- 319 theX))
					)
					(3
						((User alterEgo?) y: (+ horizon theY))
					)
					(2
						((User alterEgo?) x: (+ 0 theX))
					)
				)
				((User alterEgo?) edgeHit: 0)
			)
			(else
				(if (not argc) (= param1 0))
				(self setScript: eRS param1 prevRoomNum)
			)
		)
		(if (ego scaler?) ((ego scaler?) doit:))
	)
	
	(method (doit &tmp edge)
		(cond 
			(script (script doit:))
			((!= curRoomNum newRoomNum) 0)
			((not (cast contains: ego)) 0)
			(
				(switch (= edge ((User alterEgo?) edgeHit?))
					(NORTH north)
					(EAST east)
					(SOUTH south)
					(WEST west)
				)
				(self setScript: lRS 0 edge)
			)
		)
	)
	
	(method (doVerb theVerb)
		(if (not (super doVerb: theVerb))
			(VerbFail self theVerb)
		)
	)
	
	(method (setInset)
		(theIconBar disable: ICON_CONTROL)
		(super setInset: &rest)
	)
)

(instance lRS of Script
	
	(method (changeState newState &tmp theY theX)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theX (CelWide (ego view?) (ego loop?) (ego cel?)))
				(= theY (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(switch register
					(NORTH
						(curRoom newRoom: (curRoom north?))
					)
					(SOUTH
						(ego setMotion: PolyPath (ego x?) (+ 189 theY) self)
						(= register (curRoom south?))
					)
					(EAST
						(ego setMotion: PolyPath (+ 319 theX) (ego y?) self)
						(= register (curRoom east?))
					)
					(WEST
						(ego setMotion: PolyPath (- 0 theX) (ego y?) self)
						(= register (curRoom west?))
					)
				)
			)
			(1 (ego hide:) (= cycles 2))
			(2 (curRoom newRoom: register))
		)
	)
)

(instance eRS of Script
	
	(method (changeState newState &tmp theY theX)
		(switch (= state newState)
			(0
				(= cycles 0)
				(if (user canControl:) (theGame handsOff:))
				(= theY (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(= theX (CelWide (ego view?) (ego loop?) (ego cel?)))
				(switch register
					((client north?)
						(localproc_0490)
						(ego y: (+ (curRoom horizon?) (ego yStep?)))
						(= cycles 1)
					)
					((client south?)
						(localproc_0490)
						(ego
							y: (+ 189 theY)
							setMotion: PolyPath (ego x?) (- 189 (* (ego yStep?) 2)) self
						)
					)
					((client east?)
						(localproc_04d6)
						(ego
							x: (+ 319 (/ theX 2))
							setMotion: PolyPath (- 319 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					((client west?)
						(localproc_04d6)
						(ego
							x: (- 0 (/ theX 2))
							setMotion: PolyPath (+ 0 (* (ego xStep?) 2)) (ego y?) self
						)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(theGame handsOn: 1)
				(self dispose:)
			)
		)
	)
)
