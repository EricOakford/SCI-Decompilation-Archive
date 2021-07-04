;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1460)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use ExitFeature)
(use useObj)
(use String)
(use Print)
(use Polygon)
(use Feature)
(use Actor)
(use System)

(public
	rm1104 0
)

(instance rm1104 of KQRoom
	(properties
		picture 1460
	)
	
	(method (init)
		(super init:)
		(= south 1450)
		(redGem init:)
		(yellowGem init:)
		(blueGem init:)
		(statue init:)
		(rainDrop init:)
		(leftHand init:)
		(rightHand init:)
		(redGemArea init:)
		(blueGemArea init:)
		(sunImage init:)
		(southFeat init:)
	)
)

(instance getThePuzzlePiece of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(Prints
					{You solved the puzzle, the turquoise puzzle piece arises from the altar}
				)
				(= cycles 1)
			)
			(1 (puzzle1 init:) (self cue:))
			(2 (self dispose:))
		)
	)
)

(instance redGem of useObj
	(properties
		view 1460
		verb 47
	)
	
	(method (init)
		(cond 
			((== global166 1) (= global166 0) (self posn: 57 44 position redGemArea))
			((== global167 1) (= global167 0) (self posn: 220 39 position blueGemArea))
			((== global168 1) (= global168 0) (self posn: 180 34 position sunImage))
			((== global165 1) (= global165 0) (self posn: 167 4 position leftHand))
			((== global164 1) (= global164 0) (self posn: 120 25 position: rightHand))
		)
		(super init:)
		(self setHotspot: 8 10 49 48)
	)
	
	(method (dispose)
		(switch position
			(redGemArea (= global166 1))
			(blueGemArea (= global167 1))
			(sunImage (= global168 1))
			(leftHand (= global165 1))
			(rightHand (= global164 1))
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(Printf {gemVerb: %d} theVerb)
		(switch theVerb
			(8
				(if (Btst 28)
					(super doVerb: theVerb)
				else
					(Prints {Its a red gem.})
				)
			)
			(49
				(yellowGem
					x: (redGem x?)
					y: (redGem y?)
					position: (redGem position?)
					show:
				)
				(super doVerb: 8)
			)
			(48
				(blueGem
					x: (redGem x?)
					y: (redGem y?)
					position: (redGem position?)
					show:
				)
				(super doVerb: 8)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance yellowGem of useObj
	(properties
		x 194
		y 39
		view 1460
		loop 1
		verb 49
	)
	
	(method (init)
		(cond 
			((== global166 3) (= global166 0) (self posn: 57 44 position: redGemArea))
			((== global167 3) (= global167 0) (self posn: 220 39 position: blueGemArea))
			((== global168 3) (= global168 0) (self posn: 180 34 position: sunImage))
			((== global165 3) (= global165 0) (self posn: 167 4 position: leftHand))
			((== global164 3) (= global164 0) (self posn: 120 25 position: rightHand))
		)
		(super init:)
		(self setHotspot: 8 10 47 48)
	)
	
	(method (dispose)
		(switch position
			(redGemArea (= global166 3))
			(blueGemArea (= global167 3))
			(sunImage (= global168 3))
			(leftHand (= global165 3))
			(rightHand (= global164 3))
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (Btst 28)
					(super doVerb: theVerb)
				else
					(Prints {Its a yellow gem.})
				)
			)
			(47
				(redGem
					x: (yellowGem x?)
					y: (yellowGem y?)
					position: (yellowGem position?)
					show:
				)
				(super doVerb: 8)
			)
			(48
				(blueGem
					x: (yellowGem x?)
					y: (yellowGem y?)
					position: (yellowGem position?)
					show:
				)
				(super doVerb: 8)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance blueGem of useObj
	(properties
		x 241
		y 46
		view 1460
		loop 2
		verb 48
	)
	
	(method (init)
		(cond 
			((== global166 2) (= global166 0) (self posn: 57 44 position: redGemArea))
			((== global167 2) (= global167 0) (self posn: 220 39 position: blueGemArea))
			((== global168 2) (= global168 0) (self posn: 180 34 position: sunImage))
			((== global165 2) (= global165 0) (self posn: 167 4 position: leftHand))
			((== global164 2) (= global164 0) (self posn: 120 25 position: rightHand))
		)
		(super init:)
		(self setHotspot: 8 10 47 49)
	)
	
	(method (dispose)
		(switch position
			(redGemArea (= global166 2))
			(blueGemArea (= global167 2))
			(sunImage (= global168 2))
			(leftHand (= global165 2))
			(rightHand (= global164 2))
		)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8
				(if (Btst 28)
					(super doVerb: theVerb)
				else
					(Prints {Its a blue gem.})
				)
			)
			(47
				(redGem
					x: (blueGem x?)
					y: (blueGem y?)
					position: (blueGem position?)
					show:
				)
				(super doVerb: 8)
			)
			(49
				(yellowGem
					x: (blueGem x?)
					y: (blueGem y?)
					position: (blueGem position?)
					show:
				)
				(super doVerb: 8)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance puzzle1 of View
	(properties
		x 136
		y 66
		view 1100
		cel 1
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 8 10)
		(self setPri: 400)
	)
	
	(method (doVerb theVerb)
		(return
			(if (== theVerb 8)
				(Bset 15)
				(ego get: 5)
				(self dispose:)
				(return 1)
			else
				0
			)
		)
	)
)

(instance statue of Feature
	(properties
		noun 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			setHotspot: 8 10
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						141
						4
						136
						9
						141
						13
						136
						29
						118
						28
						115
						37
						132
						36
						134
						51
						128
						56
						133
						61
						146
						53
						166
						53
						184
						59
						184
						53
						175
						50
						171
						32
						161
						29
						172
						21
						171
						9
						183
						2
						167
						0
						164
						7
						159
						22
						159
						3
					yourself:
				)
		)
	)
)

(instance rainDrop of Feature
	(properties
		nsLeft 143
		nsTop 68
		nsRight 177
		nsBottom 109
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10)
	)
	
	(method (doVerb theVerb &tmp temp0 temp1 temp2)
		(= temp2 (String with: {The rainDrop is now facing_}))
		(= temp0 (String with: {right side up.}))
		(= temp1 (String with: {upside down.}))
		(switch theVerb
			(8
				(if
					(Print
						addText: {Do you want to turn the rainDrop around?}
						addButton: 1 {YES} 5 10
						addButton: 0 {NO} 5 25
						init:
					)
					(if (Btst 28)
						(Bclr 28)
						(Prints {You cause more light to shine through the eye.})
					else
						(Bset 28)
					)
					(if (Btst 28)
						(temp2 cat: temp0)
					else
						(temp2 cat: temp1)
					)
					(Print addText: (temp2 data?) init:)
				)
			)
			(else  (super doVerb: theVerb))
		)
		(temp2 dispose:)
		(temp0 dispose:)
		(temp1 dispose:)
	)
)

(instance leftHand of Feature
	(properties
		nsLeft 165
		nsRight 182
		nsBottom 10
		x 167
		y 4
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10 47 48 49)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(47
				(cond 
					((== (blueGem position?) leftHand)
						(redGem setHotspot: 8 10 49 48 position: leftHand show:)
						(blueGem doVerb: 8)
					)
					((== (yellowGem position?) leftHand)
						(redGem setHotspot: 8 10 49 48 position: leftHand show:)
						(yellowGem doVerb: 8)
					)
					(else (redGem setHotspot: 8 10 49 48 position: leftHand show:))
				)
			)
			(48
				(cond 
					((== (redGem position?) leftHand)
						(blueGem setHotspot: 8 10 49 47 position: leftHand show:)
						(redGem doVerb: 8)
					)
					((== (yellowGem position?) leftHand)
						(blueGem setHotspot: 8 10 49 47 position: leftHand show:)
						(yellowGem doVerb: 8)
					)
					(else
						(blueGem setHotspot: 8 10 49 47 position: leftHand show:)
						(if
							(and
								(== (redGem position?) sunImage)
								(== (yellowGem position?) rightHand)
							)
							(curRoom setScript: getThePuzzlePiece)
						)
					)
				)
			)
			(49
				(cond 
					((== (redGem position?) leftHand)
						(yellowGem
							setHotspot: 8 10 48 47
							position: leftHand
							show:
						)
						(redGem doVerb: 8)
					)
					((== (blueGem position?) leftHand)
						(yellowGem
							setHotspot: 8 10 48 47
							position: leftHand
							show:
						)
						(blueGem doVerb: 8)
					)
					(else
						(yellowGem
							setHotspot: 8 10 48 47
							position: leftHand
							show:
						)
						(if
							(and
								(== (redGem position?) sunImage)
								(== (blueGem position?) rightHand)
							)
							(curRoom setScript: getThePuzzlePiece)
						)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance rightHand of Feature
	(properties
		nsLeft 115
		nsTop 28
		nsRight 130
		nsBottom 38
		x 120
		y 25
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10 47 48 49)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(47
				(cond 
					((== (blueGem position?) rightHand)
						(redGem setHotspot: 8 10 48 49 position: rightHand show:)
						(blueGem doVerb: 8)
					)
					((== (yellowGem position?) rightHand)
						(redGem setHotspot: 8 10 48 49 position: rightHand show:)
						(yellowGem doVerb: 8)
					)
					(else (redGem setHotspot: 8 10 48 49 position: rightHand show:))
				)
			)
			(48
				(cond 
					((== (redGem position?) rightHand)
						(blueGem setHotspot: 8 10 47 49 position: rightHand show:)
						(redGem doVerb: 8)
					)
					((== (yellowGem position?) rightHand)
						(blueGem setHotspot: 8 10 47 49 position: rightHand show:)
						(yellowGem doVerb: 8)
					)
					(else
						(blueGem setHotspot: 8 10 47 49 position: rightHand show:)
						(if
							(and
								(== (redGem position?) sunImage)
								(== (yellowGem position?) leftHand)
							)
							(curRoom setScript: getThePuzzlePiece)
						)
					)
				)
			)
			(49
				(cond 
					((== (redGem position?) rightHand)
						(yellowGem
							setHotspot: 8 10 47 48
							position: rightHand
							show:
						)
						(redGem doVerb: 8)
					)
					((== (blueGem position?) rightHand)
						(yellowGem
							setHotspot: 8 10 47 48
							position: rightHand
							show:
						)
						(blueGem doVerb: 8)
					)
					(else
						(yellowGem
							setHotspot: 8 10 47 48
							position: rightHand
							show:
						)
						(if
							(and
								(== (redGem position?) sunImage)
								(== (blueGem position?) leftHand)
							)
							(curRoom setScript: getThePuzzlePiece)
						)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance redGemArea of Feature
	(properties
		nsLeft 52
		nsTop 44
		nsRight 76
		nsBottom 51
		x 57
		y 44
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10 47 48 49)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(47
				(cond 
					((== (blueGem position?) redGemArea)
						(redGem setHotspot: 8 10 48 49 position: redGemArea show:)
						(blueGem doVerb: 8)
					)
					((== (yellowGem position?) redGemArea)
						(redGem setHotspot: 8 10 48 49 position: redGemArea show:)
						(yellowGem doVerb: 8)
					)
					(else (redGem setHotspot: 8 10 48 49 position: redGemArea show:))
				)
			)
			(48
				(cond 
					((== (redGem position?) redGemArea)
						(blueGem
							setHotspot: 8 10 47 49
							position: redGemArea
							show:
						)
						(redGem doVerb: 8)
					)
					((== (yellowGem position?) redGemArea)
						(blueGem
							setHotspot: 8 10 47 49
							position: redGemArea
							show:
						)
						(yellowGem doVerb: 8)
					)
					(else
						(blueGem
							setHotspot: 8 10 47 49
							position: redGemArea
							show:
						)
					)
				)
			)
			(49
				(cond 
					((== (redGem position?) redGemArea)
						(yellowGem
							setHotspot: 8 10 47 48
							position: redGemArea
							show:
						)
						(redGem doVerb: 8)
					)
					((== (blueGem position?) redGemArea)
						(yellowGem
							setHotspot: 8 10 47 48
							position: redGemArea
							show:
						)
						(blueGem doVerb: 8)
					)
					(else
						(yellowGem
							setHotspot: 8 10 47 48
							position: redGemArea
							show:
						)
					)
				)
			)
			(else 
				(SetDebug)
				(super doVerb: theVerb)
			)
		)
	)
)

(instance blueGemArea of Feature
	(properties
		nsLeft 224
		nsTop 43
		nsRight 257
		nsBottom 49
		x 220
		y 39
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10 47 48 49)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(47
				(cond 
					((== (blueGem position?) blueGemArea)
						(redGem
							setHotspot: 8 10 48 49
							position: blueGemArea
							show:
						)
						(blueGem doVerb: 8)
					)
					((== (yellowGem position?) blueGemArea)
						(redGem
							setHotspot: 8 10 48 49
							position: blueGemArea
							show:
						)
						(yellowGem doVerb: 8)
					)
					(else
						(redGem
							setHotspot: 8 10 48 49
							position: blueGemArea
							show:
						)
					)
				)
			)
			(48
				(cond 
					((== (redGem position?) blueGemArea)
						(blueGem
							setHotspot: 8 10 47 49
							position: blueGemArea
							show:
						)
						(redGem doVerb: 8)
					)
					((== (yellowGem position?) blueGemArea)
						(blueGem
							setHotspot: 8 10 47 49
							position: blueGemArea
							show:
						)
						(yellowGem doVerb: 8)
					)
					(else
						(blueGem
							setHotspot: 8 10 47 49
							position: blueGemArea
							show:
						)
					)
				)
			)
			(49
				(cond 
					((== (redGem position?) blueGemArea)
						(yellowGem
							setHotspot: 8 10 47 48
							position: blueGemArea
							show:
						)
						(redGem doVerb: 8)
					)
					((== (blueGem position?) blueGemArea)
						(yellowGem
							setHotspot: 8 10 47 48
							position: blueGemArea
							show:
						)
						(blueGem doVerb: 8)
					)
					(else
						(yellowGem
							setHotspot: 8 10 47 48
							position: blueGemArea
							show:
						)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance sunImage of Feature
	(properties
		nsLeft 177
		nsTop 33
		nsRight 216
		nsBottom 44
		x 180
		y 34
	)
	
	(method (init)
		(super init:)
		(self setHotspot: 8 10 47 48 49)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(47
				(cond 
					((== (blueGem position?) sunImage)
						(redGem setHotspot: 8 10 48 49 position: sunImage show:)
						(blueGem doVerb: 8)
					)
					((== (yellowGem position?) sunImage)
						(redGem setHotspot: 8 10 48 49 position: sunImage show:)
						(yellowGem doVerb: 8)
					)
					(else
						(redGem setHotspot: 8 10 48 49 position: sunImage show:)
						(if
							(and
								(or
									(== (blueGem position?) leftHand)
									(== (blueGem position?) rightHand)
								)
								(or
									(== (yellowGem position?) leftHand)
									(== (yellowGem position?) rightHand)
								)
							)
							(curRoom setScript: getThePuzzlePiece)
						)
					)
				)
			)
			(48
				(cond 
					((== (redGem position?) sunImage)
						(blueGem setHotspot: 8 10 47 49 position: sunImage show:)
						(redGem doVerb: 8)
					)
					((== (yellowGem position?) sunImage)
						(blueGem setHotspot: 8 10 47 49 position: sunImage show:)
						(yellowGem doVerb: 8)
					)
					(else (blueGem setHotspot: 8 10 47 49 position: sunImage show:))
				)
			)
			(49
				(cond 
					((== (redGem position?) sunImage)
						(yellowGem
							setHotspot: 8 10 47 48
							position: sunImage
							show:
						)
						(redGem doVerb: 8)
					)
					((== (blueGem position?) sunImage)
						(yellowGem
							setHotspot: 8 10 47 48
							position: sunImage
							show:
						)
						(blueGem doVerb: 8)
					)
					(else
						(yellowGem
							setHotspot: 8 10 47 48
							position: sunImage
							show:
						)
					)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance southFeat of ExitFeature
	(properties
		nsLeft 21
		nsTop 130
		nsRight 289
		nsBottom 142
		exitDir SOUTH
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (dispose)
		(walkHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((and (& (event type?) walkEvent) (self onMe: event))
					(curRoom newRoom: 1450)
					(event claimed: TRUE)
				)
				((and scratch (not (event type?)) (self onMe: event))
					(= theExitFeature self)
					((self scratch?) doit:)
					(return (event claimed: TRUE))
				)
			)
		)
	)
)
