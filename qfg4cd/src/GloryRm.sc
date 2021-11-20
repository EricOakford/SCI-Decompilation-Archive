;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_ROOM)
(include game.sh) (include "28.shm")
(use Main)
(use Game)
(use System)


(class GloryRm of Room
	(properties
		feaList 0
		exitList 0
		topX -1
		leftY -1
		bottomX -1
		rightY -1
	)
	
	(method (init foo)
		(if
			(and
				(OneOf curRoomNum
					551 552 553 554 555 556 557 558 559 560
					561 562 563 564 565 566 567 568 569 570
					571 572 573 574 575 576 577 578 579 580
					581 582 583 584 585 586 587 588 589 590
					591 592 593 250 260 270 280 290 300 440
					460 480 500 520 600 790 800 810
				)
				(< 2700 Clock)
				(< Clock 2801)
			)
			(PalVary PalVaryMergeSource (+ (curRoom picture?) 1))
		)
		(CyclePalette)
		(CyclePalette_13)
		(if
			(not
				(OneOf curRoomNum
					551 552 553 554 555 556 557 558 559 560
					561 562 563 564 565 566 567 568 569 570
					571 572 573 574 575 576 577 578 579 580
					581 582 583 584 585 586 587 588 589 590
					591 592 593 810 720 800 632
				)
			)
			(= global470 (= global365 (= global366 0)))
			(= gABad2Health (= gABad3Health (= gABad4Health 0)))
		)
		(= number curRoomNum)
		(= perspective picAngle)
		(if (not plane)
			(= plane thePlane)
		)
		(self drawPic: picture style)
		(if (and argc foo)
			(Palette PalIntensity 0 255 100)
		)
		(RemapColors RemapByPct 254 50)
	)
	
	(method (doit)
		(if script
			(script doit:)
		)
	)
	
	(method (dispose)
		(ego noun: N_HERO)
		(if obstacles
			(obstacles dispose:)
			(= obstacles 0)
		)
		(if feaList
			(feaList dispose:)
			(= feaList 0)
		)
		(if exitList
			(exitList dispose:)
			(= exitList 0)
		)
		(if (!= plane thePlane)
			(plane dispose:)
			(= plane 0)
		)
		(regions delete: self)
		(if script
			(script dispose:)
		)
		(if timer
			(timer dispose: delete:)
		)
		(sounds eachElementDo: #clean self)
		(DisposeScript number)
	)
	
	(method (doVerb theVerb)
		(if (not (talkers size:))
			(super doVerb: theVerb)
		)
	)
	
	(method (addPoly obj &tmp i)
		(if (not feaList)
			(= feaList (List new:))
		)
		(for ((= i 0)) (< i argc) ((+= i 2))
			(feaList
				add:
					((pObject new:)
						showSelf: [obj i] [obj (+ i 1)]
						yourself:
					)
			)
		)
	)
	
	(method (leaveRoom)
	)
	
	(method (doTorch)
	)
)

(class pObject of Object
	(properties
		polyID 0
		intensity 50
		linted 0
	)
	
	(method (doit)
		(cond 
			((polyID onMe: (ego x?) (ego y?))
				(if (not linted)
					(= linted TRUE)
					(Palette PalLoad 66 85 intensity)
				)
			)
			(linted
				(= linted FALSE)
				(Palette PalLoad 66 85 100)
			)
		)
	)
	
	(method (dispose)
		(polyID dispose:)
		(theDoits delete: self)
		(super dispose:)
	)
	
	(method (showSelf thePolyID theIntensity)
		(= polyID thePolyID)
		(= intensity theIntensity)
		(= linted 0)
		(theDoits add: self)
	)
)
