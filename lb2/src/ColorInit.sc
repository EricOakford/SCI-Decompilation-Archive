;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include sci.sh)
(use Main)

(public
	ColorInit 0
)

(procedure (ColorInit)
	(if (> (Graph grGET_COLOURS) 16)
		(= global151 0)
		(= global152 (Palette palFIND_COLOR 63 63 63))
		(= global153 (Palette palFIND_COLOR 95 95 95))
		(= global154 (Palette palFIND_COLOR 127 127 127))
		(= global155 (Palette palFIND_COLOR 159 159 159))
		(= global156 (Palette palFIND_COLOR 191 191 191))
		(= myHighlightColor (Palette palFIND_COLOR 223 223 223))
		(= global158 (Palette palFIND_COLOR 151 27 27))
		(= global159 (Palette palFIND_COLOR 223 71 71))
		(= global160 (Palette palFIND_COLOR 235 135 135))
		(= global161 (Palette palFIND_COLOR 187 187 35))
		(= global162 (Palette palFIND_COLOR 219 219 39))
		(= global163 (Palette palFIND_COLOR 223 223 71))
		(= global164 (Palette palFIND_COLOR 15 87 15))
		(= global165 (Palette palFIND_COLOR 27 151 27))
		(= global166 (Palette palFIND_COLOR 71 223 71))
		(= global167 (Palette palFIND_COLOR 135 235 135))
		(= myInsideColor (Palette palFIND_COLOR 23 23 119))
		(= global169 (Palette palFIND_COLOR 35 35 187))
		(= global170 (Palette palFIND_COLOR 71 71 223))
		(= global171 (Palette palFIND_COLOR 135 135 235))
		(= myTopBordColor (Palette palFIND_COLOR 219 39 219))
		(= myBotBordColor (Palette palFIND_COLOR 223 71 223))
		(= global174 (Palette palFIND_COLOR 71 223 223))
		(= global175 (Palette palFIND_COLOR 135 235 235))
		(= myLowlightColor (Palette palFIND_COLOR 143 107 56))
		(= myBackColor 15)
	else
		(= global151 0)
		(= global152 7)
		(= global153 8)
		(= global154 7)
		(= global155 7)
		(= global156 7)
		(= myHighlightColor 15)
		(= global158 4)
		(= global159 12)
		(= global160 12)
		(= global161 6)
		(= global162 14)
		(= global163 14)
		(= global164 2)
		(= global165 2)
		(= global166 10)
		(= global167 10)
		(= myInsideColor 1)
		(= global169 1)
		(= global170 9)
		(= global171 9)
		(= myTopBordColor 5)
		(= myBotBordColor 13)
		(= global174 3)
		(= global175 11)
		(= myBackColor 14)
	)
)
