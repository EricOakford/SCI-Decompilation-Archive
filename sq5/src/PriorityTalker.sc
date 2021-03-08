;;; Sierra Script 1.0 - (do not remove this comment)
(script# PRITALK)
(include game.sh)
(use Main)
(use Talker)


(class PriorityTalker of Talker
	(properties
		priBits 0
	)
	
	(method (dispose dWD)
		(if (and mouth underBits)
			(mouth cel: 0)
			(DrawCel
				(mouth view?)
				(mouth loop?)
				0
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				priority
			)
		)
		(if (and mouth (mouth cycler?))
			(if ((mouth cycler?) respondsTo: #cue)
				((mouth cycler?) cue:)
			)
			(mouth setCycle: 0)
		)
		(if (or (not argc) dWD)
			(if (and eyes underBits)
				(eyes setCycle: 0 cel: 0)
				(DrawCel
					(eyes view?)
					(eyes loop?)
					0
					(+ (eyes nsLeft?) nsLeft)
					(+ (eyes nsTop?) nsTop)
					priority
				)
			)
			(self hide:)
		)
		(super dispose: dWD)
	)
	
	(method (hide)
		(Graph GRestoreBits underBits)
		(= underBits 0)
		(Graph GRestoreBits priBits)
		(= priBits 0)
		(Graph GReAnimate nsTop nsLeft nsBottom nsRight)
		(if theIconBar
			(theIconBar enable:)
		)
	)
	
	(method (show &tmp pnv)
		(if (not underBits)
			(= underBits
				(Graph GSaveBits nsTop nsLeft nsBottom nsRight VMAP)
			)
		)
		(if (not priBits)
			(= priBits
				(Graph GSaveBits nsTop nsLeft nsBottom nsRight PMAP)
			)
		)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(if bust
			(DrawCel
				(bust view?)
				(bust loop?)
				(bust cel?)
				(+ (bust nsLeft?) nsLeft)
				(+ (bust nsTop?) nsTop)
				priority
			)
		)
		(if eyes
			(DrawCel
				(eyes view?)
				(eyes loop?)
				(eyes cel?)
				(+ (eyes nsLeft?) nsLeft)
				(+ (eyes nsTop?) nsTop)
				priority
			)
		)
		(if mouth
			(DrawCel
				(mouth view?)
				(mouth loop?)
				(mouth cel?)
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				priority
			)
		)
		(DrawCel view loop cel nsLeft nsTop priority)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
		(PicNotValid pnv)
	)
	
	(method (cycle obj &tmp theCel [str 100])
		(if (and obj (obj cycler?))
			(= theCel (obj cel?))
			((obj cycler?) doit:)
			(if (!= theCel (obj cel?))
				(DrawCel
					(obj view?)
					(obj loop?)
					(obj cel?)
					(+ (obj nsLeft?) nsLeft)
					(+ (obj nsTop?) nsTop)
					priority
				)
				(obj
					nsRight:
						(+
							(obj nsLeft?)
							(CelWide (obj view?) (obj loop?) (obj cel?))
						)
				)
				(obj
					nsBottom:
						(+
							(obj nsTop?)
							(CelHigh (obj view?) (obj loop?) (obj cel?))
						)
				)
				(Graph
					GShowBits
					(+ (obj nsTop?) nsTop)
					(+ (obj nsLeft?) nsLeft)
					(+ (obj nsBottom?) nsTop)
					(+ (obj nsRight?) nsLeft)
					1
				)
			)
		)
	)
)
