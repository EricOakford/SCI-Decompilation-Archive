;;; Sierra Script 1.0 - (do not remove this comment)
(script# GLORY_TALKER) ;59
(include game.sh)
(use Talker)
(use System)


(class GloryTalker of Talker
	(properties
		frameView 934
		frame 1
		backColor 33
	)
	
	(method (show &tmp pnv)
		(if (not underBits)
			(= underBits
				(Graph GSaveBits nsTop nsLeft nsBottom nsRight VMAP)
			)
		)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(Graph GFillRect
			nsTop
			nsLeft
			nsBottom
			nsRight
			1
			backColor
			-1
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if bust
			(DrawCel
				(bust view?)
				(bust loop?)
				(bust cel?)
				(+ (bust nsLeft?) nsLeft)
				(+ (bust nsTop?) nsTop)
				-1
			)
		)
		(if eyes
			(DrawCel
				(eyes view?)
				(eyes loop?)
				(eyes cel?)
				(+ (eyes nsLeft?) nsLeft)
				(+ (eyes nsTop?) nsTop)
				-1
			)
		)
		(if mouth
			(DrawCel
				(mouth view?)
				(mouth loop?)
				(mouth cel?)
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(DrawCel frameView frame 0 nsLeft nsTop -1)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
		(PicNotValid pnv)
	)
	
	(method (setSize)
		(eyes cycleSpeed: 2)
		(= nsLeft x)
		(= nsTop y)
		(= nsRight
			(+
				nsLeft
				(Max
					(if view (CelWide view loop cel) else 0)
					(CelWide frameView 0 frame)
					(if (IsObject bust)
						(+
							(bust nsLeft?)
							(CelWide (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsLeft?)
							(CelWide (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsLeft?)
							(CelWide (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
		(= nsBottom
			(+
				nsTop
				(Max
					(if view (CelHigh view loop cel) else 0)
					(CelHigh frameView 0 frame)
					(if (IsObject bust)
						(+
							(bust nsTop?)
							(CelHigh (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsTop?)
							(CelHigh (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsTop?)
							(CelHigh (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
	)
)
