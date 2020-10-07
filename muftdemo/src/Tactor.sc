;;; Sierra Script 1.0 - (do not remove this comment)
(script# 90)
(include game.sh)
(use Procs)
(use Intrface)
(use Talker)
(use User)
(use Actor)


(class Tactor of Actor
	(properties
		doCode 0
		selection 0
		talkerObj 0
		talkerID 0
		client 0
	)
	
	(method (init)
		(self setUp:)
		(super init: &rest)
	)
	
	(method (doVerb theVerb &tmp [str 40])
		(cond 
			((!= theVerb verbTalk)
				(super doVerb: theVerb &rest)
			)
			((IsObject doCode)
				(doCode doit: theVerb)
			)
			(else
				(StrCpy @str (if description else name))
				(StrAt @str 0 (ToLower (StrAt @str 0)))
				(Printf 90 0 @str)
			)
		)
	)
	
	(method (incSel)
		(++ selection)
	)
	
	(method (relPosn param1 param2 param3)
		(self
			posn: (+ (param1 x?) param2) (+ (param1 y?) param3)
		)
	)
	
	(method (setUp)
	)
)

(class Tego of Ego
	(properties
		doCode 0
		selection 0
		talkerObj 0
		talkerID 0
	)
	
	(method (incSel)
		(++ selection)
	)
)

(class TalkerObj of Talker
	(properties
		facingDir dirE
	)
	
	(method (show param1 &tmp theWidth)
		(bust cel: param1)
		(cond 
			((and (== facingDir dirE) (== loop 1))
				(= loop 0)
				(bust loop: 6)
				(eyes loop: 4)
				(mouth loop: 2)
				(= theWidth (CelWide view 0 0))
				(bust
					nsLeft: (-
						(- theWidth (CelWide (bust view?) 6 0))
						(bust nsLeft?)
					)
				)
				(eyes
					nsLeft: (-
						(- theWidth (CelWide (eyes view?) 4 0))
						(eyes nsLeft?)
					)
				)
				(mouth
					nsLeft: (-
						(- theWidth (CelWide (mouth view?) 2 0))
						(mouth nsLeft?)
					)
				)
			)
			((and (== facingDir dirW) (!= loop 1))
				(= loop 1)
				(bust loop: 7)
				(eyes loop: 5)
				(mouth loop: 3)
				(= theWidth (CelWide view 0 0))
				(bust
					nsLeft: (-
						(- theWidth (CelWide (bust view?) 7 0))
						(bust nsLeft?)
					)
				)
				(eyes
					nsLeft: (-
						(- theWidth (CelWide (eyes view?) 5 0))
						(eyes nsLeft?)
					)
				)
				(mouth
					nsLeft: (-
						(- theWidth (CelWide (mouth view?) 3 0))
						(mouth nsLeft?)
					)
				)
			)
		)
		(super show:)
	)
	
	(method (setUp theBust theEyes theMouth)
		(= bust theBust)
		(= eyes theEyes)
		(= mouth theMouth)
	)
)
