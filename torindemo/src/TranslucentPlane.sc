;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64884)
(include sci.sh)
(use Cast)
(use Plane)
(use Actor)


(class TranslucentPlane of Plane
	(properties
		scratch 0
		resX -1
		resY -1
		left 0
		top 0
		right 0
		bottom 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		vanishingX 0
		vanishingY 0
		coordType 0
		picture -2
		style $0000
		priority -1
		back 0
		bitmap 0
		casts 0
		mirrored 0
		nScreenSizeX 0
		getLocalY 2
	)
	
	(method (init &tmp translucentPlaneFindData translucentPlaneDoDouble temp2 temp3 temp4)
		(super init: &rest)
		(self addCast: (Cast new:))
		(RemapColors 2 237 45)
		(= translucentPlaneFindData (self findData:))
		(= translucentPlaneDoDouble (self doDouble:))
		(= temp2 (* getLocalY 32))
		(= temp4 (* translucentPlaneFindData 32))
		(= temp3 (* translucentPlaneDoDouble 32))
		((View new:)
			view: -636
			x: 0
			y: 0
			setPri: 0
			setScale:
			scaleX: temp4
			scaleY: temp3
			maxScale: 20480
			init: (casts at: 0)
		)
		(if getLocalY
			((View new:)
				view: -636
				x: 0
				y: 0
				setPri: 1
				scaleSignal: 1
				scaleX: temp4
				scaleY: temp2
				maxScale: 20480
				init: (casts at: 0)
			)
			((View new:)
				view: -636
				x: 0
				y: getLocalY
				setPri: 1
				scaleSignal: 1
				scaleX: temp2
				scaleY: (* (- translucentPlaneDoDouble getLocalY) 32)
				maxScale: 20480
				init: (casts at: 0)
			)
			((View new:)
				view: -636
				x: (- translucentPlaneFindData getLocalY)
				y: getLocalY
				setPri: 1
				setScale:
				scaleX: temp2
				scaleY: (* (- translucentPlaneDoDouble getLocalY) 32)
				maxScale: 20480
				init: (casts at: 0)
			)
			((View new:)
				view: -636
				x: getLocalY
				y: (- translucentPlaneDoDouble getLocalY)
				setPri: 1
				scaleSignal: 1
				scaleX: (*
					(- translucentPlaneFindData (+ getLocalY getLocalY))
					32
				)
				scaleY: temp2
				maxScale: 20480
				init: (casts at: 0)
			)
		)
	)
)
