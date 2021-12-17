;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64869)
(include sci.sh)
(use Plane)
(use Actor)
(use System)


(instance voTemp of View
	(properties)
)

(class TiledBitmap of Obj
	(properties
		scratch 0
		bBorder 1
		nSizeMode 0
		nWidth 0
		nHeight 0
		nTilesX 0
		nTilesY 0
		nBaseTilesX 0
		nBaseTilesY 0
		nOffsetX 0
		nOffsetY 0
		nBaseWidth 0
		nBaseHeight 0
		nBorderHeight 0
		nBorderWidth 0
		nLeftBorderWidth 0
		nRightBorderWidth 0
		nTopBorderHeight 0
		nBottomBorderHeight 0
		bitmap 0
		vTiles -1
	)
	
	(method (init theVTiles param2 param3 theNSizeMode theBBorder)
		(super init: &rest)
		(if (< argc 3)
			(MonoOut {invalid init of TiledBitmap})
			(return)
		)
		(= vTiles theVTiles)
		(if (> argc 3) (= nSizeMode theNSizeMode))
		(if (> argc 4) (= bBorder theBBorder))
		(self resize: param2 param3)
	)
	
	(method (dispose)
		(if bitmap (Bitmap 1 bitmap) (= bitmap 0))
		(super dispose: &rest)
	)
	
	(method (resize theNWidth theNHeight &tmp theBBorder temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 theNLeftBorderWidth theNTopBorderHeight temp11)
		(if (< argc 2)
			(MonoOut {Invalid resize of TiledBitmap})
			(return)
		)
		(if (not (ResCheck 128 vTiles))
			(MonoOut {no view %hd for TiledBitmap} vTiles)
			(return)
		)
		(voTemp view: vTiles loop: 0 cel: 0)
		(if
		(or (< (= temp5 (NumLoops voTemp)) 1) (> temp5 2))
			(MonoOut {wrong number of loops view %hd} vTiles)
			(return)
		)
		(if (== temp5 1)
			(= theBBorder 0)
			(= temp6 (NumCels voTemp))
		else
			(= theBBorder bBorder)
			(= temp6 (NumCels voTemp))
			(voTemp loop: 1)
			(= temp7 (NumCels voTemp))
		)
		(if
		(or (!= temp6 1) (and theBBorder (!= temp7 8)))
			(MonoOut
				{Error: tiles view %hd should have 1 cel in loop 0, 8 cels in loop 1}
			)
			(return)
		)
		(= nBaseWidth (CelWide vTiles 0 0))
		(= nBaseHeight (CelHigh vTiles 0 0))
		(if theBBorder
			(= nBorderHeight (CelHigh vTiles 1 0))
			(= nBorderWidth (CelWide vTiles 1 2))
			(= nLeftBorderWidth (CelWide vTiles 1 0))
			(= nRightBorderWidth (CelWide vTiles 1 1))
			(= nTopBorderHeight (CelHigh vTiles 1 2))
			(= nBottomBorderHeight (CelHigh vTiles 1 3))
			(if
				(or
					(!= nBaseHeight nBorderHeight)
					(!= nBaseWidth nBorderWidth)
				)
				(= temp11 0)
			else
				(= temp11 1)
			)
			(= temp8 0)
			(if (!= nBorderHeight (CelHigh vTiles 1 1))
				(MonoOut {view %hd left/right borders don't match})
				(= temp8 1)
			)
			(if (!= nBorderWidth (CelWide vTiles 1 3))
				(MonoOut {view %hd top/bottom borders don't match})
				(= temp8 1)
			)
			(if
				(or
					(!= nLeftBorderWidth (CelWide vTiles 1 4))
					(!= nTopBorderHeight (CelHigh vTiles 1 4))
				)
				(MonoOut {view %hd TL corner wrong size})
				(= temp8 1)
			)
			(if
				(or
					(!= nRightBorderWidth (CelWide vTiles 1 5))
					(!= nTopBorderHeight (CelHigh vTiles 1 5))
				)
				(MonoOut {view %hd TR corner wrong size})
				(= temp8 1)
			)
			(if
				(or
					(!= nLeftBorderWidth (CelWide vTiles 1 6))
					(!= nBottomBorderHeight (CelHigh vTiles 1 6))
				)
				(MonoOut {view %hd BL corner wrong size})
				(= temp8 1)
			)
			(if
				(or
					(!= nRightBorderWidth (CelWide vTiles 1 7))
					(!= nBottomBorderHeight (CelHigh vTiles 1 7))
				)
				(MonoOut {view %hd BR corner wrong size})
				(= temp8 1)
			)
			(if temp8 (return))
		else
			(= nBorderHeight nBaseHeight)
			(= nBorderWidth nBaseWidth)
			(= temp11 1)
			(= nBottomBorderHeight
				(= nTopBorderHeight
					(= nRightBorderWidth (= nLeftBorderWidth 0))
				)
			)
		)
		(switch nSizeMode
			(0
				(= nTilesX (/ theNWidth nBorderWidth))
				(if (!= theNWidth (* nTilesX nBorderWidth))
					(++ nTilesX)
				)
				(= nTilesY (/ theNHeight nBorderHeight))
				(if (!= theNHeight (* nTilesY nBorderHeight))
					(++ nTilesY)
				)
			)
			(1
				(= temp1
					(- theNWidth (+ nLeftBorderWidth nRightBorderWidth))
				)
				(= temp2
					(- theNHeight (+ nTopBorderHeight nBottomBorderHeight))
				)
				(if
				(or (< temp1 nBaseWidth) (< temp2 nBaseHeight))
					(MonoOut {requested max size too small for tiles})
				)
				(= nTilesX (/ temp1 nBaseWidth))
				(= nTilesY (/ temp2 nBaseHeight))
			)
		)
		(if temp11
			(= nBaseTilesX nTilesX)
			(= nBaseTilesY nTilesY)
		else
			(= temp1 (* nTilesX nBorderWidth))
			(= temp2 (* nTilesY nBorderHeight))
			(= nBaseTilesX (/ temp1 nBaseWidth))
			(if (!= temp1 (* nBaseTilesX nBaseWidth))
				(++ nBaseTilesX)
			)
			(= nBaseTilesY (/ temp2 nBaseHeight))
			(if (!= temp2 (* nBaseTilesY nBaseHeight))
				(++ nBaseTilesY)
			)
		)
		(if theBBorder
			(= nWidth
				(+
					nLeftBorderWidth
					(* nBorderWidth nTilesX)
					nRightBorderWidth
				)
			)
			(= nHeight
				(+
					nTopBorderHeight
					(* nBorderHeight nTilesY)
					nBottomBorderHeight
				)
			)
			(= nOffsetX
				(+
					nLeftBorderWidth
					(/ (- (* nBorderWidth nTilesX) theNWidth) 2)
				)
			)
			(= nOffsetY
				(+
					nTopBorderHeight
					(/ (- (* nBorderHeight nTilesY) theNHeight) 2)
				)
			)
		else
			(= nWidth theNWidth)
			(= nHeight theNHeight)
			(= nOffsetY (= nOffsetX 0))
		)
		(if bitmap (Bitmap 1 bitmap) (= bitmap 0))
		(= bitmap (Bitmap 0 nWidth nHeight 255 255))
		(= theNLeftBorderWidth nLeftBorderWidth)
		(= theNTopBorderHeight nTopBorderHeight)
		(= temp3 0)
		(while (< temp3 nBaseTilesY)
			(= temp4 0)
			(while (< temp4 nBaseTilesX)
				(Bitmap
					3
					bitmap
					vTiles
					0
					0
					theNLeftBorderWidth
					theNTopBorderHeight
				)
				(= theNLeftBorderWidth
					(+ theNLeftBorderWidth nBaseWidth)
				)
				(++ temp4)
			)
			(= theNLeftBorderWidth nLeftBorderWidth)
			(= theNTopBorderHeight
				(+ theNTopBorderHeight nBaseHeight)
			)
			(++ temp3)
		)
		(if theBBorder
			(= theNLeftBorderWidth 0)
			(= theNTopBorderHeight 0)
			(Bitmap
				3
				bitmap
				vTiles
				1
				4
				theNLeftBorderWidth
				theNTopBorderHeight
			)
			(= theNLeftBorderWidth
				(+ theNLeftBorderWidth nLeftBorderWidth)
			)
			(= temp3 0)
			(while (< temp3 nTilesX)
				(Bitmap
					3
					bitmap
					vTiles
					1
					2
					theNLeftBorderWidth
					theNTopBorderHeight
				)
				(= theNLeftBorderWidth
					(+ theNLeftBorderWidth nBorderWidth)
				)
				(++ temp3)
			)
			(Bitmap
				3
				bitmap
				vTiles
				1
				5
				theNLeftBorderWidth
				theNTopBorderHeight
			)
			(= theNLeftBorderWidth 0)
			(= theNTopBorderHeight nTopBorderHeight)
			(= temp3 0)
			(while (< temp3 nTilesY)
				(Bitmap
					3
					bitmap
					vTiles
					1
					0
					theNLeftBorderWidth
					theNTopBorderHeight
				)
				(= theNTopBorderHeight
					(+ theNTopBorderHeight nBorderHeight)
				)
				(++ temp3)
			)
			(= theNLeftBorderWidth
				(+ nLeftBorderWidth (* nTilesX nBorderWidth))
			)
			(= theNTopBorderHeight nTopBorderHeight)
			(Bitmap
				5
				bitmap
				theNLeftBorderWidth
				theNTopBorderHeight
				(- (+ theNLeftBorderWidth nRightBorderWidth) 1)
				(- (+ theNTopBorderHeight (* nBorderHeight nTilesY)) 1)
				255
			)
			(= temp3 0)
			(while (< temp3 nTilesY)
				(Bitmap
					3
					bitmap
					vTiles
					1
					1
					theNLeftBorderWidth
					theNTopBorderHeight
				)
				(= theNTopBorderHeight
					(+ theNTopBorderHeight nBorderHeight)
				)
				(++ temp3)
			)
			(= theNLeftBorderWidth 0)
			(= theNTopBorderHeight
				(+ nTopBorderHeight (* nBorderHeight nTilesY))
			)
			(Bitmap
				5
				bitmap
				theNLeftBorderWidth
				theNTopBorderHeight
				(- nWidth 1)
				(- (+ theNTopBorderHeight nBottomBorderHeight) 1)
				255
			)
			(Bitmap
				3
				bitmap
				vTiles
				1
				6
				theNLeftBorderWidth
				theNTopBorderHeight
			)
			(= theNLeftBorderWidth
				(+ theNLeftBorderWidth nLeftBorderWidth)
			)
			(= temp3 0)
			(while (< temp3 nTilesX)
				(Bitmap
					3
					bitmap
					vTiles
					1
					3
					theNLeftBorderWidth
					theNTopBorderHeight
				)
				(= theNLeftBorderWidth
					(+ theNLeftBorderWidth nBorderWidth)
				)
				(++ temp3)
			)
			(Bitmap
				3
				bitmap
				vTiles
				1
				7
				theNLeftBorderWidth
				theNTopBorderHeight
			)
		)
	)
	
	(method (getBitmap param1 &tmp theBitmap)
		(= theBitmap bitmap)
		(if (and argc param1) (= bitmap 0))
		(return theBitmap)
	)
)

(class TiledView of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		nWidth 0
		nHeight 0
		nOffsetX 0
		nOffsetY 0
	)
	
	(method (init param1 param2 param3 param4)
		(cond 
			((< argc 2) (MonoOut {Invalid init of TiledView -- not enough params}))
			((== argc 2) (TiledBitmap init: view param1 param2))
			((== argc 3) (TiledBitmap init: view param1 param2 param3))
			((> argc 3) (TiledBitmap init: view param1 param2 param3 param4))
		)
		(= bitmap (TiledBitmap getBitmap: 1))
		(= nWidth (TiledBitmap nWidth?))
		(= nHeight (TiledBitmap nHeight?))
		(= nOffsetX (TiledBitmap nOffsetX?))
		(= nOffsetY (TiledBitmap nOffsetY?))
		(super init: &rest)
	)
	
	(method (dispose &tmp theBitmap)
		(= theBitmap 0)
		(if bitmap (= theBitmap bitmap) (= bitmap 0))
		(super dispose:)
		(if theBitmap (Bitmap 1 theBitmap))
	)
)

(class TiledPlane of Plane
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
		picture -1
		style $0000
		priority -1
		back 0
		bitmap 0
		casts 0
		mirrored 0
		oMyFeatures 0
		nOffsetX 0
		nOffsetY 0
		voBG 0
		vTiles -1
		nSizeMode 0
		bBorder 1
	)
	
	(method (init param1 param2 param3 param4 theVTiles theNSizeMode theBBorder &tmp [temp0 2])
		(if (< argc 5)
			(MonoOut {Not enough params to init of TiledPlane})
			(return)
		)
		(super init: param1 param2 param3 param4 &rest)
		(= vTiles theVTiles)
		(if (> argc 5) (= nSizeMode theNSizeMode))
		(if (> argc 6) (= bBorder theBBorder))
		(self setRect: param1 param2 param3 param4)
	)
	
	(method (dispose)
		(if voBG (voBG dispose:) (= voBG 0))
		(super dispose: &rest)
	)
	
	(method (setRect param1 param2 param3 param4 &tmp voBGNWidth voBGNHeight)
		(= voBGNWidth (+ (- param3 param1) 1))
		(= voBGNHeight (+ (- param4 param2) 1))
		(if
			(or
				(!= voBGNWidth (self getWidth:))
				(!= voBGNHeight (self getHeight:))
				(not voBG)
			)
			(if voBG (voBG dispose:))
			(= voBG (TiledView new:))
			(voBG name: {voBG} setPri: 0 view: vTiles)
			(voBG init: voBGNWidth voBGNHeight nSizeMode bBorder self)
			(= nOffsetX (voBG nOffsetX?))
			(= nOffsetY (voBG nOffsetY?))
			(= voBGNWidth (voBG nWidth?))
			(= voBGNHeight (voBG nHeight?))
		)
		(super
			setRect: left top (- (+ left voBGNWidth) 1) (- (+ top voBGNHeight) 1)
		)
	)
)
