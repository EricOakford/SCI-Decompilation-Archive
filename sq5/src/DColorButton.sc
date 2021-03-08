;;; Sierra Script 1.0 - (do not remove this comment)
(script# COLORBTN)
(include game.sh)
(use Intrface)
(use Dialog)
(use System)


(class DColorButton of DButton
	(properties
		mode 0
		nfc 0
		nbc 7
		hfc 15
		hbc 7
		sfc 7
		sbc 7
	)
	
	(method (track event &tmp in lastIn fgColor bgColor)
		(return
			(if (== 1 (event type?))
				(= lastIn 0)
				(repeat
					((= event (Event new: leaveIt)) localize:)
					(if (!= (= in (self check: event)) lastIn)
						(if (& state $0008)
							(= fgColor sfc)
							(= bgColor sbc)
						else
							(= fgColor hfc)
							(= bgColor hbc)
						)
						(Display text
							p_at nsLeft nsTop
							p_width (- nsRight nsLeft)
							p_font font
							p_color fgColor
							p_back bgColor
							p_mode mode
						)
						(= lastIn in)
					)
					(event dispose:)
					(breakif (not (StillDown)))
				)
				(if in
					(if (& state $0008)
						(= fgColor sfc)
						(= bgColor sbc)
					else
						(= fgColor hfc)
						(= bgColor hbc)
					)
					(Display text
						p_at nsLeft nsTop
						p_width (- nsRight nsLeft)
						p_font font
						p_color fgColor
						p_back bgColor
						p_mode mode
					)
				)
				(return in)
			else
				(return self)
			)
		)
	)
	
	(method (setSize args &tmp [r 4])
		(TextSize @[r 0] text font (if argc args else 0))
		(= [r 2] (+ [r 2] 2))
		(= [r 3] (+ [r 3] 2))
		(= nsBottom (+ nsTop [r 2]))
		(= [r 3] (* (/ (+ [r 3] 15) 16) 16))
		(= nsRight (+ [r 3] nsLeft))
	)
	
	(method (draw &tmp fgColor bgColor)
		(if (& state $0008)
			(= fgColor hfc)
			(= bgColor hbc)
		else
			(= fgColor nfc)
			(= bgColor nbc)
		)
		(Display text
			p_at nsLeft nsTop
			p_width (- nsRight nsLeft)
			p_font font
			p_color fgColor
			p_back bgColor
			p_mode mode
		)
	)
)
