;;; Sierra Script 1.0 - (do not remove this comment)
(script# TALKER)
(include game.sh)
(use Main)
(use Intrface)
(use Sync)
(use RandCyc)
(use Actor)
(use System)


(class RTRandCycle kindof RandCycle
	(method (doit &tmp theTime)
		(if (> count (= theTime (GetTime)))
			(if (> (- theTime cycleCnt) (client cycleSpeed?)) 
				(client cel:(self nextCel:))
				(= cycleCnt (GetTime))
			)
		else
			(client cel:	0)
			(self cycleDone:)
		)
	)

	(method (init obj theTime whoCares)
		(super init: obj)
		(client cel:	0)
		(= cycleCnt (GetTime))
		(if (>= argc 2)			
			(= count (+ (GetTime) theTime)) 
			(if (>= argc 3)		
				(= caller whoCares)
			)
		else 
			(= count -1)
		)
	)

)

(class Talker kindof Prop
	(properties
		bust	NULL
		eyes	NULL
		mouth	NULL
		ticks	NULL
		disposeWhenDone TRUE
		caller	NULL
	)

;;;	(methods 
;;;		init 		  		;initialize, add to fastCast, show, and optionally say
;;;		dispose			;delete from fastCast, and optionally cue caller and hide 
;;;		say 				;startText or startAudio based on cDAudio	global
;;;		startAudio 		;start talker sync'ing to audio
;;;		startText 		;print message and move mouth and eyes
;;;		cycle				;cycle mouth and eyes and redraw them
;;;		show				;draw all components of talker on screen
;;;		hide				;erase talker from screen
;;;		doit				;a fastCast animation cycle
;;;		handleEvent		;dispose if mouseDown or ENTER
;;;	)

	(method (init bustView prop syncedProp modNum charNum msgNum dWD whoCares
			&tmp pnv  
			)
		(if argc
			(= bust bustView)
			(if (>= argc 2)
				(= eyes prop)
				(if (>= argc 3)
					(= mouth syncedProp)
					(if (>= argc 7)
						(= disposeWhenDone dWD)
						(if (>= argc 8)
							(= caller whoCares)
						)
					)
				)
			)
		)
		(self show:)
		(if (>= argc 4)
			(self	say: modNum charNum msgNum disposeWhenDone caller)
		)
	)

	(method (show &tmp pnv)
		(= nsRight 	
			(+ nsLeft 
				(Max
					(CelWide view loop cel)
					(if (IsObject bust)
						(+ bust nsLeft (CelWide (bust view?)(bust loop?)(bust cel?)))
					)
					(if (IsObject eyes)
						(+ eyes nsLeft (CelWide (eyes view?)(eyes loop?)(eyes cel?)))
					)
					(if (IsObject mouth)
						(+ mouth nsLeft (CelWide (mouth view?)(mouth loop?)(mouth cel?)))
					)
				)
			)
		)
		(= nsBottom 
			(+ nsTop 
				(Max
					(CelHigh view loop cel)
					(if (IsObject bust)
						(+ bust nsTop (CelHigh (bust view?)(bust loop?)(bust cel?)))
					)
					(if (IsObject eyes)
						(+ eyes nsTop (CelHigh (eyes view?)(eyes loop?)(eyes cel?)))
					)
					(if (IsObject mouth)
						(+ mouth nsTop (CelHigh (mouth view?)(mouth loop?)(mouth cel?)))
					)
				)
			)
		)
		(if (not underBits)
			(= underBits (Graph GSaveBits nsTop nsLeft nsBottom nsRight VMAP))
		)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(if bust
			(DrawCel	
				(bust view?) (bust loop?) (bust cel?) 
				(+ (bust nsLeft?) nsLeft) (+ (bust nsTop?) nsTop)
				-1
			)
		)
		(if eyes
			(DrawCel	
				(eyes	view?) (eyes loop?) (eyes cel?)	
				(+ (eyes nsLeft?) nsLeft) (+ (eyes nsTop?) nsTop)
				-1
			)
		)
		(if mouth
			(DrawCel	
				(mouth view?) (mouth loop?) (mouth cel?)	
				(+ (mouth nsLeft?) nsLeft) (+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(DrawCel view loop cel nsLeft nsTop -1)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight VMAP)
		(PicNotValid pnv)
	)

	(method (say modNum charNum msgNum dWD whoCares)
		(if (>= argc 4)
			(= disposeWhenDone dWD)
			(if (>= argc 5)
				(= caller whoCares)
			)
		)	
		(if cDAudio
			(self startAudio: modNum charNum msgNum)
		else
			(self startText: modNum charNum msgNum)
		)
		(if fastCast
			(fastCast add: self)
		else
			(= fastCast (EventHandler new:))
			(fastCast name: {fastCast} add: self)
		)
		(= ticks (+ ticks 60 (GetTime)))
	)

	(method (startText modNum charNum msgNum &tmp [buffer 500])
		;; Draw Talker face and text and leave it for a calculated
		;; amount of time
		;; TEST HOOK
;;;		(if modelessDialog 
;;;			(modelessDialog dispose:)
;;;		)
		(Format @buffer  modNum charNum)
		(= ticks (* 5 (StrLen @buffer)))
		(if mouth (mouth setCycle: RTRandCycle ticks))
		(if eyes	 (eyes  setCycle: RTRandCycle ticks))
		(Print @buffer #at:x y #dispose:)
	)

	(method (startAudio modNum charNum msgNum &tmp theAudio)
		;; Draw Talker face and start audio 
		;; TEST HOOK
		(= theAudio modNum)
	  	(DoAudio WPlay	theAudio)
		(if mouth (mouth setCycle: MouthSync theAudio)) 
	  	(= ticks (DoAudio Play theAudio))
		(if eyes (eyes	setCycle: RTRandCycle ticks))
;		(Display (Format @wrkStr "%d" theAudio)
;			p_at:	(+ nsLeft 15) (+ nsTop 3)
;			p_font:	999
;			p_color:	1
;		)
	)

	(method (cycle obj &tmp theCel)
		;; call cycler doit and redraw obj if it has changed
		(if (and obj (obj cycler?))
			(= theCel 	(obj cel?))
			((obj cycler?)		doit:)
			(if (!= theCel (obj cel?))
				(DrawCel	
					(obj	view?)
					(obj	loop?)
					(obj	cel?)	
					(+ (obj nsLeft?) nsLeft)
					(+ (obj nsTop?) nsTop)
					-1
				)
				(obj 
					nsRight: 
						(+ obj nsLeft 
							(CelWide (obj view?) (obj loop?)  (obj cel?))
						)
				)
				(obj 
					nsBottom: 
						(+  obj nsTop
							(CelHigh (obj view?) (obj loop?)  (obj cel?))
						)
				)
		 		(Graph GShowBits 
					(+ (obj nsTop?) 		nsTop)
					(+ (obj nsLeft?) 	nsLeft)
					(+ (obj nsBottom?) 	nsTop)
					(+ (obj nsRight?) 	nsLeft)
					VMAP
				)
			)
		)
	)

	(method (doit)
		(if (> (GetTime) ticks)
			(self dispose:disposeWhenDone)
			(return)
		)
		(if eyes		(self	cycle: eyes))
		(if mouth 	(self cycle: mouth))
	)

	(method (handleEvent event)
		(if (super handleEvent:event)
			(return)
		)
		(if (or 
				(& (event type?) mouseDown)
				(& (event type?) userEvent)
				(and
					(& (event type?) keyDown)
					(== (event message?) ENTER)
				)
			)
			(event claimed: TRUE)
			(self dispose: disposeWhenDone)
		)
	)

	(method (hide)
		(Graph 	GRestoreBits underBits)
		(=	underBits 0)
		(Graph 	GReAnimate nsTop nsLeft nsBottom nsRight)
	)

	(method (dispose dWD &tmp toCue)
		(fastCast delete: self release:)
		(fastCast dispose:)
		(= fastCast 0)
		(if (or (not argc) dWD)
			
			;; this is here because MouthSync is a non-standard 
			;; Cycler and thus needs special attention.
			(if (and mouth (mouth cycler?)) 
				(if ((mouth cycler?) respondsTo: #cue)
					((mouth cycler?)  cue:)
				)
				(mouth setCycle:0)
			)

			(cond
				(cDAudio
					(DoAudio Stop)
				)
				(modelessDialog
					(modelessDialog dispose:)
				)
			)
			(if eyes	(eyes 	setCycle:0))
			(self hide:)
		)
		(if caller
			;; I know this looks stupid, but It gets rid of an
			;; incidious little bug.
			(= toCue caller)
			(= caller 0)
			(toCue cue:)
		)
		;; in lue of super dispose:
		(DisposeClone self)
	)
)

