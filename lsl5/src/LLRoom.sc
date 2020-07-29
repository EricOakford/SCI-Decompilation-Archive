;;; Sierra Script 1.0 - (do not remove this comment)
(script# LLROOM)
(include game.sh)
(use Main)
(use Intrface)
(use PolyPath)
(use Game)
(use Invent)
(use User)
(use System)

(public
	eRS 0
)

(local
	[chargerPosn 7] = [160 64 108 260 19 146]
)
(procedure (localproc_049c)
	(cond 
		((< (ego x?) 0) (ego x: (+ 0 (* (ego xStep?) 2))))
		((> (ego x?) 319) (ego x: (- 319 (* (ego xStep?) 2))))
	)
)

(procedure (localproc_04e9)
	(cond 
		((< (ego y?) (curRoom horizon?)) (ego y: (+ (curRoom horizon?) (* (ego yStep?) 2))))
		((> (ego y?) 189) (ego y: (- 189 (* (ego yStep?) 2))))
	)
)

(instance roomControls of Controls
	(properties
		name "controls"
	)
)

(class LLRoom of Room
	
	(method (init &tmp egoWidth egoHeight i chargerLoop)
		(= number curRoomNum)
		(= controls roomControls)
		(if (== ((Inventory at: iCharger) owner?) curRoom)
			(= i 0)
			(while (!= [chargerPosn i] 0)
				(if (== [chargerPosn i] curRoomNum)
					(if (== ((Inventory at: iCamcorder) owner?) (Inventory at: iCharger))
						(= chargerLoop 4)
					else
						(= chargerLoop 3)
					)
					((ScriptID CHARGER 3)
						x: [chargerPosn (++ i)]
						y: [chargerPosn (++ i)]
						setLoop: chargerLoop
						init:
						approachVerbs: verbLook verbDo verbUse
					)
					(break)
				)
				(= i (+ i 3))
			)
		)
		(= perspective picAngle)
		(if picture
			(self drawPic: picture)
		)
		(cond 
			((not (cast contains: ego)) 0)
			(script 0)
			((not ((User alterEgo?) edgeHit?)) 0)
			((OneOf style SCROLLRIGHT SCROLLLEFT SCROLLUP SCROLLDOWN)
				(= egoWidth
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
				(= egoHeight
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
					(NORTH
						((User alterEgo?) y: southEdge)
					)
					(WEST
						((User alterEgo?) x: (- eastEdge egoWidth))
					)
					(SOUTH
						((User alterEgo?) y: (+ horizon egoHeight))
					)
					(EAST
						((User alterEgo?) x: (+ westEdge egoWidth))
					)
				)
				((User alterEgo?) edgeHit: westEdge)
			)
			(else (self setScript: eRS 0 prevRoomNum))
		)
	)
	
	(method (doit &tmp edge)
		(cond 
			(script (script doit:))
			((not (cast contains: ego)) 0)
			(
				(= edge
					(switch ((User alterEgo?) edgeHit?)
						(NORTH north)
						(EAST east)
						(SOUTH south)
						(WEST west)
					)
				)
				(self setScript: lRS 0 edge)
			)
		)
	)
	
	(method (doVerb theVerb &tmp [str 200])
		(return
			(if (and (== theVerb verbLook) lookStr)
				(Format @str 18 0 lookStr)
				(TimePrint @str)
				(return TRUE)
			else
				(return FALSE)
			)
		)
	)
)

(instance lRS of Script
	
	(method (changeState newState &tmp egoHeight egoWidth)
		(switch (= state newState)
			(0
				(HandsOff)
				(= egoWidth (CelWide (ego view?) (ego loop?) (ego cel?)))
				(= egoHeight (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(switch register
					((client north?)
						(curRoom newRoom: register)
					)
					((client south?)
						(ego setMotion: PolyPath (ego x?) (+ southEdge egoHeight) self)
					)
					((client east?)
						(ego setMotion: PolyPath (+ eastEdge egoWidth) (ego y?) self)
					)
					((client west?)
						(ego setMotion: PolyPath (- westEdge egoWidth) (ego y?) self)
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
	
	(method (changeState newState &tmp egoHeight egoWidth)
		(switch (= state newState)
			(0
				(= cycles 0)
				(HandsOff)
				(= egoHeight (CelHigh (ego view?) (ego loop?) (ego cel?)))
				(= egoWidth (CelWide (ego view?) (ego loop?) (ego cel?)))
				(switch register
					((client north?)
						(localproc_049c)
						(ego y: (+ (curRoom horizon?) (ego yStep?)))
						(= cycles 1)
					)
					((client south?)
						(localproc_049c)
						(ego
							y: (+ 189 egoHeight)
							setMotion: PolyPath (ego x?) (- southEdge (* (ego yStep?) 2)) self
						)
					)
					((client east?)
						(localproc_04e9)
						(ego
							x: (+ 319 (/ egoWidth 2))
							setMotion: PolyPath (- eastEdge (* (ego xStep?) 2)) (ego y?) self
						)
					)
					((client west?)
						(localproc_04e9)
						(ego
							x: (- 0 (/ egoWidth 2))
							setMotion: PolyPath (+ westEdge (* (ego xStep?) 2)) (ego y?) self
						)
					)
					(else  (= cycles 1))
				)
			)
			(1
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
